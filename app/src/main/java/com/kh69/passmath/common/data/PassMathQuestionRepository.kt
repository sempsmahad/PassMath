package com.kh69.passmath.common.data

import com.kh69.passmath.common.data.api.QuestionLoaderApi
import com.kh69.passmath.common.domain.model.NetworkException
import com.kh69.passmath.common.domain.model.pagination.PaginatedQuestions
import com.kh69.passmath.common.domain.model.question.Question
import com.kh69.passmath.common.domain.repositories.QuestionRepository
import io.reactivex.Flowable
import okhttp3.Cache
import retrofit2.HttpException
import javax.inject.Inject



class PassMathQuestionRepository @Inject constructor(
    private val api: QuestionLoaderApi,
    private val cache: Cache,
    private val apiAnimalMapper: ApiAnimalMapper,
    private val apiPaginationMapper: ApiPaginationMapper
) : QuestionRepository {

    // fetch these from shared preferences, after storing them in onboarding screen
    private val postcode = "07097"
    private val maxDistanceMiles = 100

    override fun getAnimals(): Flowable<List<Animal>> {
        return cache.getNearbyAnimals()
            .distinctUntilChanged()
            .map { animalList ->
                animalList.map { it.animal.toAnimalDomain(it.photos, it.videos, it.tags) }
            }
    }

    override suspend fun requestMoreAnimals(pageToLoad: Int, numberOfItems: Int): PaginatedAnimals {
        try {
            val (apiAnimals, apiPagination) = api.getNearbyAnimals(
                pageToLoad,
                numberOfItems,
                postcode,
                maxDistanceMiles
            )

            return PaginatedAnimals(
                apiAnimals?.map { apiAnimalMapper.mapToDomain(it) }.orEmpty(),
                apiPaginationMapper.mapToDomain(apiPagination)
            )
        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }

    override suspend fun storeAnimals(animals: List<AnimalWithDetails>) {
        // Organizations have a 1-to-many relation with animals, so we need to insert them first in
        // order for Room not to complain about foreign keys being invalid (since we have the
        // organizationId as a foreign key in the animals table)
        val organizations = animals.map { CachedOrganization.fromDomain(it.details.organization) }

        cache.storeOrganizations(organizations)
        cache.storeNearbyAnimals(animals.map { CachedAnimalAggregate.fromDomain(it) })
    }

    override fun getQuestions(): Flowable<List<Question>> {
        TODO("Not yet implemented")
    }

    override suspend fun requestMoreQuestions(
        pageToLoad: Int,
        numberOfItems: Int
    ): PaginatedQuestions {
        TODO("Not yet implemented")
    }

    override suspend fun storeQuestions(questions: List<Question>) {
        TODO("Not yet implemented")
    }

//  TODO: Uncomment for remote search
//  override suspend fun searchAnimalsRemotely(
//      pageToLoad: Int,
//      searchParameters: SearchParameters,
//      numberOfItems: Int
//  ): PaginatedAnimals {
//
//    val (apiAnimals, apiPagination) = api.searchAnimalsBy(
//        searchParameters.name,
//        searchParameters.age,
//        searchParameters.type,
//        pageToLoad,
//        numberOfItems,
//        postcode,
//        maxDistanceMiles
//    )
//
//    return PaginatedAnimals(
//        apiAnimals?.map { apiAnimalMapper.mapToDomain(it) }.orEmpty(),
//        apiPaginationMapper.mapToDomain(apiPagination)
//    )
//  }
}