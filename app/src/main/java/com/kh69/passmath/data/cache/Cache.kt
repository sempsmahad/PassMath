package com.kh69.passmath.data.cache

import com.kh69.passmath.data.cache.model.cachedquestion.CachedQuestionAggregate
import io.reactivex.Flowable

interface Cache {
    fun getQuestions(): Flowable<List<CachedQuestionAggregate>>
    fun storeQuestions(questions: List<CachedQuestionAggregate>)

    fun searchQuestionsBy(
        year: Int,
        paper: String,
        section: String,
        topic: String
    ): Flowable<List<CachedQuestionAggregate>>
}