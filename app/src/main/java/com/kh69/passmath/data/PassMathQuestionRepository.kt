package com.kh69.passmath.data

import android.app.appsearch.SearchResults
import com.kh69.passmath.data.api.PassMathApi
import com.kh69.passmath.data.api.model.mappers.ApiPaginationMapper
import com.kh69.passmath.data.api.model.mappers.ApiQuestionMapper
import com.kh69.passmath.data.cache.Question
import com.kh69.passmath.domain.model.pagination.PaginatedQuestions
import com.kh69.passmath.domain.repositories.QuestionsRepository
import com.kh69.passmath.search.domain.model.SearchParameters
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

    override fun searchCachedQuestionsBy(searchParameters: SearchParameters): Flowable<com.kh69.passmath.search.domain.model.SearchResults> {
        TODO("Not yet implemented")
    }

    override suspend fun searchQuestionsRemotely(
        pageToLoad: Int,
        searchParameters: SearchParameters,
        numberOfItems: Int
    ): PaginatedQuestions {
        TODO("Not yet implemented")
    }

}