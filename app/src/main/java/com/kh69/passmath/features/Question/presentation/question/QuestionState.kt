package com.kh69.passmath.features.question.presentation.question

import com.kh69.passmath.features.question.domain.model.Question

data class QuestionState(
    val questions: List<Question> = emptyList(),
    val answerIsVisible: Boolean = false,
    val questionIndex: Int = 1,
    val number_of_questions: Int = 0
)