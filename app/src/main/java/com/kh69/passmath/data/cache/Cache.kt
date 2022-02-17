package com.kh69.passmath.data.cache

import io.reactivex.Flowable
import java.time.Year

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