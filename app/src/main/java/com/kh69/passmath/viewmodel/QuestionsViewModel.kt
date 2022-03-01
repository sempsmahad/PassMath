package com.kh69.passmath.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kh69.passmath.data.QuizRepository
import com.kh69.passmath.data.cache.Question
import com.kh69.passmath.data.model.QuizState

class QuestionsViewModel(repository: QuizRepository) : ViewModel() {
    private val questionAndAnswers = MediatorLiveData<Question>()
    private val allQuestionAndAllAnswers = repository.getSavedQuestions()
    private val currentState = MediatorLiveData<QuizState>()
    private val currentQuestion = MutableLiveData<Int>()
    private var score: Int = 0


    fun getCurrentState(): LiveData<QuizState> = currentState


    private fun addStateSources() {
        currentState.addSource(currentQuestion)
        { currentQuestionNumber ->
            if (currentQuestionNumber ==
                allQuestionAndAllAnswers.value?.size
            ) {
                currentState.postValue(
                    QuizState.FinishState(
                        currentQuestionNumber, score
                    )
                )
            }
        }
        currentState.addSource(allQuestionAndAllAnswers)
        { allQuestionsAndAnswers ->
            if (allQuestionsAndAnswers.isEmpty()) {
                currentState.postValue(QuizState.EmptyState)
            }
        }

        currentState.addSource(questionAndAnswers)
        { questionAndAnswers ->
            currentState.postValue(QuizState.DataState(questionAndAnswers))
        }
    }


}