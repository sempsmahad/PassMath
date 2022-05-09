package com.kh69.passmath.data

import com.kh69.passmath.MathApp
import com.kh69.passmath.data.cache.Question
import com.kh69.passmath.data.cache.QuestionDAO

class Repository : QuizRepository {
    private val questionDAO: QuestionDAO by lazy {
        MathApp.database.questionDAO()
    }

    private val allQuestions by lazy {
        questionDAO.getQuestions()
    }

    override fun getSavedQuestions() = allQuestions

    override suspend fun saveQuestion(question: Question) {
        questionDAO.addQuestion(question)
    }

    override suspend fun deleteQuestions() {
        questionDAO.clearQuestions()
    }
}