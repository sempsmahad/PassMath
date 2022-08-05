package com.kh69.passmath.features.question.presentation.question

sealed class QuestionEvent {
    data class ViewAnswer(val question_id: String, val isVisible: Boolean) : QuestionEvent()
    object switch_to_previous : QuestionEvent()
    object switch_to_next : QuestionEvent()
}