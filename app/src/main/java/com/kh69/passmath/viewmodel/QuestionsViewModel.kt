package com.kh69.passmath.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kh69.passmath.data.QuizRepository
import com.kh69.passmath.data.cache.Question
import com.kh69.passmath.data.model.QuizState

class QuestionsViewModel(repository: QuizRepository) : ViewModel() {
    private val questionList = repository.getSavedQuestions()
    private val selectedQuestion = MutableLiveData<Int>()
    private val currentState = MediatorLiveData<QuizState>()

    fun getCurrentState(): LiveData<QuizState> = currentState

    private fun setSelectedQuestionTo(position: Int) {
        selectedQuestion.postValue(position)
    }

    private fun addStateSources() {
        currentState.addSource(selectedQuestion)
        { currentQuestionPosition ->
            currentState.postValue(QuizState.SelectedState(currentQuestionPosition))
        }

        currentState.addSource(questionList)
        { questions ->
            if (questions.isEmpty()) {
                currentState.postValue(QuizState.EmptyState)
            } else {
                currentState.postValue(QuizState.DataState(questions))
            }
        }
    }

    fun nextQuestion(position: Int) {
        setSelectedQuestionTo(position)
    }


    init {
        currentState.postValue(QuizState.LoadingState)
        addStateSources()
    }

}