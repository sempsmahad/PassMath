package com.kh69.passmath.features.question.presentation.question

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kh69.passmath.features.question.domain.usecase.QuestionUseCases

class QuestionViewModelProviderFactory(val questionUseCases: QuestionUseCases,val savedStateHandle: SavedStateHandle) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuestionViewModel(questionUseCases,savedStateHandle) as T
    }


}