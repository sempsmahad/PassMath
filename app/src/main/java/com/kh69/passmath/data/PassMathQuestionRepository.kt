package com.kh69.passmath.data

import android.app.appsearch.SearchResults
import com.kh69.passmath.data.api.PassMathApi
import com.kh69.passmath.data.api.model.mappers.ApiPaginationMapper
import com.kh69.passmath.data.api.model.mappers.ApiQuestionMapper
import com.kh69.passmath.domain.repositories.QuestionsRepository
import com.kh69.passmath.utils.DispatchersProvider
import io.reactivex.Flowable
import okhttp3.Cache
import javax.inject.Inject

class PassMathQuestionRepository @Inject constructor(
    private val api: PassMathApi,
    private val cache: Cache,
    private val apiQuestionMapper: ApiQuestionMapper,
    private val apiPaginationMapper: ApiPaginationMapper,
    dispatchersProvider: DispatchersProvider
): QuestionsRepository {
    override fun getAnimals(): Flowable<List<Animal>> {
        TODO("Not yet implemented")
    }

    override suspend fun requestMoreAnimals(pageToLoad: Int, numberOfItems: Int): PaginatedAnimals {
        TODO("Not yet implemented")
    }

    override suspend fun storeAnimals(animals: List<AnimalWithDetails>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimalTypes(): List<String> {
        TODO("Not yet implemented")
    }

    override fun getAnimalAges(): List<AnimalWithDetails.Details.Age> {
        TODO("Not yet implemented")
    }

    override fun searchCachedAnimalsBy(searchParameters: SearchParameters): Flowable<SearchResults> {
        TODO("Not yet implemented")
    }

    override suspend fun searchAnimalsRemotely(
        pageToLoad: Int,
        searchParameters: SearchParameters,
        numberOfItems: Int
    ): PaginatedAnimals {
        TODO("Not yet implemented")
    }
}