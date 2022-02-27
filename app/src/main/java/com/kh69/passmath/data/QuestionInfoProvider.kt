package com.kh69.passmath.data

import android.util.Log
import com.kh69.passmath.Response
import com.kh69.passmath.data.cache.Question
import com.kh69.passmath.remote.APIUtils
import retrofit2.Call
import retrofit2.Callback

object QuestionInfoProvider {

    val questionList = initQuestionList()

    private fun initQuestionList(): MutableList<Question> {
        var questions = mutableListOf<Question>()

        val call: Call<Response> = APIUtils.getQuestionService().questions
        call.enqueue(object : Callback<Response> {
            override fun onResponse(
                call: Call<Response>,
                response: retrofit2.Response<Response>
            ) {
                if (response.isSuccessful) {
                    questions = response.body()!!.alldata
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("ERROR: ", t.message!!)
            }
        })

        return questions
    }

}