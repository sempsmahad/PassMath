package com.kh69.passmath.domain.repositories

import com.kh69.passmath.data.cache.Question
import com.kh69.passmath.domain.model.pagination.PaginatedQuestions
import com.kh69.passmath.search.domain.model.SearchParameters
import com.kh69.passmath.search.domain.model.SearchResults
import io.reactivex.Flowable

interface QuestionsRepository {

    fun getQuestions(): Flowable<List<Question>>
    suspend fun requestMoreQuestions(pageToLoad: Int, numberOfItems: Int): PaginatedQuestions
    suspend fun storeQuestions(questions: List<Question>)
    fun searchCachedQuestionsBy(searchParameters: SearchParameters): Flowable<SearchResults>

    suspend fun searchQuestionsRemotely(
        pageToLoad: Int,
        searchParameters: SearchParameters,
        numberOfItems: Int
    ): PaginatedQuestions
}