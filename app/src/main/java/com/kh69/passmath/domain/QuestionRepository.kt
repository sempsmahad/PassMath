package com.kh69.passmath.domain

import com.kh69.passmath.data.cache.Question
import com.kh69.passmath.domain.model.pagination.PaginatedQuestions
import io.reactivex.Flowable

interface QuestionRepository {
    fun getQuestions(): Flowable<List<Question>>
    suspend fun requestMoreQuestions(pageToLoad: Int, numberOfItems: Int): PaginatedQuestions
    suspend fun storeQuestions(animals: List<Question>)
}