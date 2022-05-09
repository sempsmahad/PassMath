package com.kh69.passmath.data

import androidx.lifecycle.LiveData


interface QuizRepository {
    fun getSavedQuestions(): LiveData<List<Question>>
    suspend fun saveQuestion(question: Question)
//    suspend fun saveAnswer(answer: Answer)
//    fun getQuestionAndAllAnswers():
//            LiveData<List<QuestionAndAllAnswers>>

    suspend fun deleteQuestions()
}
