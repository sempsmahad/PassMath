package com.kh69.passmath.common.data

import com.kh69.passmath.common.data.api.QuestionLoaderApi
import com.kh69.passmath.common.data.api.model.mappers.ApiPaginationMapper
import com.kh69.passmath.common.data.api.model.mappers.ApiQuestionMapper
import com.kh69.passmath.common.data.cache.Cache
import com.kh69.passmath.common.data.cache.model.cachedquestion.CachedQuestion
import com.kh69.passmath.common.domain.model.NetworkException
import com.kh69.passmath.common.domain.model.pagination.PaginatedQuestions
import com.kh69.passmath.common.domain.model.question.Question
import com.kh69.passmath.common.domain.repositories.QuestionRepository
import io.reactivex.Flowable
import retrofit2.HttpException
import javax.inject.Inject

class PassMathQuestionRepository @Inject constructor(
    private val api: QuestionLoaderApi,
    private val cache: Cache,
    private val apiQuestionMapper: ApiQuestionMapper,
    private val apiPaginationMapper: ApiPaginationMapper
) : QuestionRepository {

    // fetch these from shared preferences, after storing them in onboarding screen
    private val postcode = "07097"
    private val maxDistanceMiles = 100

    override fun getQuestions(): Flowable<List<Question>> {
        return cache.getQuestions()
            .distinctUntilChanged()
            .map { questionList ->
                questionList.map { it.toDomain() }
            }
    }

    override suspend fun requestMoreQuestions(
        pageToLoad: Int,
        numberOfItems: Int
    ): PaginatedQuestions {
        try {
            val (apiQuestions, apiPagination) = api.getQuestions(
                pageToLoad,
                numberOfItems
            )

            return PaginatedQuestions(
                apiQuestions?.map { apiQuestionMapper.mapToDomain(it) }.orEmpty(),
                apiPaginationMapper.mapToDomain(apiPagination)
            )
        } catch (exception: HttpException) {
            throw NetworkException(exception.message ?: "Code ${exception.code()}")
        }
    }

    override suspend fun storeQuestions(questions: List<Question>) {
        cache.storeQuestions(questions.map { CachedQuestion.fromDomain(it) })
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