package com.kh69.passmath.data.cache.model.cachedquestion

import androidx.room.Embedded
import com.kh69.passmath.data.cache.Question


data class CachedQuestionAggregate(
    @Embedded
    val question: CachedQuestion
) {

    companion object {
        fun fromDomain(question: Question): CachedQuestionAggregate {
            return CachedQuestionAggregate(
                question = CachedQuestion.fromDomain(question)
            )
        }
    }
}