package com.kh69.passmath.ui.questionCards

import android.text.TextUtils.isEmpty
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kh69.passmath.data.Question
import com.kh69.passmath.data.QuestionInfoProvider.questionList
import com.kh69.passmath.data.Resource
import com.kh69.passmath.data.model.QuizState
import com.kh69.passmath.data.source.QtnRepository

class QuestionCardsViewModel constructor(repository: QtnRepository) : ViewModel() {
    val questions:LiveData<Resource<List<Question>>>  = repository.getQuestions()
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

        currentState.addSource(questions)
        { questions ->
            if (questions.data?.isEmpty() == true) {
                currentState.postValue(QuizState.EmptyState)
            } else {
                currentState.postValue(QuizState.DataState(questions.data!!))
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