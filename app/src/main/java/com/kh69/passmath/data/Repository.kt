package com.kh69.passmath.data

import com.kh69.passmath.MathApp
import com.kh69.passmath.data.source.local.QuestionsDao

class Repository : QuizRepository {
    private val mQuestionsDao: QuestionsDao by lazy {
        MathApp.database.questionDAO()
    }

    private val allQuestions by lazy {
        mQuestionsDao.getQuestions()
    }

    override fun getSavedQuestions() = allQuestions

    override suspend fun saveQuestion(question: Question) {
        mQuestionsDao.addQuestion(question)
    }

    override suspend fun deleteQuestions() {
        mQuestionsDao.clearQuestions()
    }
}