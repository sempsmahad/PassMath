package com.kh69.passmath.data

import androidx.lifecycle.LiveData
import com.kh69.passmath.Answer
import com.kh69.passmath.data.cache.Question
import com.raywenderlich.android.droidquiz.data.model.Answer
import com.raywenderlich.android.droidquiz.data.model.Question
import com.raywenderlich.android.droidquiz.data.model.QuestionAndAllAnswers


interface QuizRepository {
    fun getSavedQuestions(): LiveData<List<Question>>
    suspend fun saveQuestion(question: Question)
    suspend fun saveAnswer(answer: Answer)
    fun getQuestionAndAllAnswers():
            LiveData<List<QuestionAndAllAnswers>>

    suspend fun deleteQuestions()
}
