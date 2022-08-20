package com.kh69.passmath.common.domain.repositories

import com.kh69.passmath.common.domain.model.pagination.PaginatedQuestions
import com.kh69.passmath.common.domain.model.question.Question
import io.reactivex.Flowable

interface QuestionRepository {
    fun getQuestions(): Flowable<List<Question>>
    suspend fun requestMoreQuestions(pageToLoad: Int, numberOfItems: Int): PaginatedQuestions
    suspend fun storeQuestions(questions: List<Question>)

//  TODO: Uncomment for remote search
//  suspend fun searchQuestionsRemotely(
//      pageToLoad: Int,
//      searchParameters: SearchParameters,
//      numberOfItems: Int
//  ): PaginatedQuestions
}