package com.kh69.passmath.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kh69.passmath.QuestionAdapter
import com.kh69.passmath.Response
import com.kh69.passmath.data.QuestionInfoProvider
import com.kh69.passmath.data.QuizRepository
import com.kh69.passmath.remote.APIUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

class MainViewModel(private val repository: QuizRepository) :
    ViewModel() {
    fun prepopulateQuestions() {
        viewModelScope.launch(Dispatchers.IO) {

            for (question in QuestionInfoProvider.questionList) {
                repository.saveQuestion(question)
            }

        }
    }

    fun clearQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteQuestions()
        }
    }
}