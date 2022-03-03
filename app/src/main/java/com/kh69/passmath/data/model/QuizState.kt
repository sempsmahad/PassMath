package com.kh69.passmath.data.model

import com.kh69.passmath.data.cache.Question

sealed class QuizState {
    object LoadingState : QuizState()
    data class DataState(val data: List<Question>) :
        QuizState()

    object EmptyState : QuizState()
    data class SelectedState(
        val position: Int
    ) : QuizState()
}