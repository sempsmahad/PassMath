package com.kh69.passmath.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kh69.passmath.data.QuizRepository

class QuestionsViewModel(repository: QuizRepository) : ViewModel()  {
    private val allQuestionAndAllAnswers = repository.getSavedQuestions()

    fun getCurrentState(): LiveData<QuizState> = currentState
    private fun changeCurrentQuestion() {
        currentQuestion.postValue(currentQuestion.value?.inc())
    }

}