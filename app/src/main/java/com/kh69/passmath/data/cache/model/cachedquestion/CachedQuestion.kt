package com.kh69.passmath.data.cache.model.cachedquestion

import com.kh69.passmath.data.cache.Question


data class CachedQuestion(
    val questionId: String,
    val text: String,
    val year: String,
    val paper: String,
    val section: String,
    val topic: String,
    val answer: String,
    val katex_question: String,
    val katex_answer: String,
    val edited: String
) {

    companion object {
        fun fromDomain(question: Question): CachedQuestion {
            return CachedQuestion(
                question.id,
                question.text,
                question.year.toString(),
                question.paper.toString(),
                question.section,
                question.topic,
                question.answer,
                question.katex_question,
                question.katex_answer,
                question.edited.toString()
            )
        }
    }

    fun toDomain(): Question {
        return Question(
            questionId,
            text,
            year.toInt(),
            paper.toInt(),
            section,
            topic,
            answer,
            katex_question,
            katex_answer,
            edited.toBoolean()
        )
    }
}