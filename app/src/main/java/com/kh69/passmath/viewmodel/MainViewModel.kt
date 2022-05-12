package com.kh69.passmath.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kh69.passmath.Response
import com.kh69.passmath.data.QuestionInfoProvider
import com.kh69.passmath.data.QuizRepository
import com.kh69.passmath.data.Question
import com.kh69.passmath.data.source.remote.APIUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

class MainViewModel(private val repository: QuizRepository) :
    ViewModel() {
    fun prepopulateQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
//            QuestionInfoProvider.initQuestionList()

            var questions = mutableListOf<Question>()

            val call: Call<Response> = APIUtils.getQuestionService().questions
            call.enqueue(object : Callback<Response> {
                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    if (response.isSuccessful) {
                        questions = response.body()!!.alldata
                        Log.e("ERROR: ", "" + QuestionInfoProvider.questionList.size)
                    }
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Log.e("ERROR: ", t.message!!)
                }
            })

            if (questions.isEmpty()) {
                Thread.sleep(9_000)
            }

            Log.e("ERROR3: ", "" + questions.size)
            for (question in questions) {
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