package com.kh69.passmath.questions.presentation

import com.kh69.passmath.common.presentation.Event
import com.kh69.passmath.common.presentation.model.UIQuestion


data class QuizViewState(
    val loading: Boolean = true,
    val questions: List<UIQuestion> = emptyList(),
    val noMoreQuestions: Boolean = false,
    val failure: Event<Throwable>? = null
)