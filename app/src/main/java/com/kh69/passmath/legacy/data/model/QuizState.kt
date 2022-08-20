package com.kh69.passmath.legacy.data.model

import com.kh69.passmath.legacy.data.Question

sealed class QuizState {
    object LoadingState : QuizState()
    data class DataState(val data: List<Question>) :
        QuizState()

    object EmptyState : QuizState()
    data class SelectedState(
        val position: Int
    ) : QuizState()
}