package com.kh69.passmath.data

import androidx.lifecycle.LiveData
import com.kh69.passmath.Answer
import com.kh69.passmath.data.cache.Question
import com.raywenderlich.android.droidquiz.QuizApplication
import com.raywenderlich.android.droidquiz.data.db.QuizDao
import com.raywenderlich.android.droidquiz.data.model.Answer
import com.raywenderlich.android.droidquiz.data.model.Question
import com.raywenderlich.android.droidquiz.data.model.QuestionAndAllAnswers

class Repository : QuizRepository {
    private val quizDao: QuizDao by lazy {
        QuizApplication.database.quizDao()
    }
    private val allQuestions by lazy {
        quizDao.getAllQuestions()
    }
    private val allQuestionsAndAllAnswers by lazy {
        quizDao.getQuestionAndAllAnswers()
    }

    override fun getSavedQuestions() = allQuestions

    override suspend fun saveQuestion(question: Question) {
        quizDao.insert(question)
    }

    override suspend fun saveAnswer(answer: Answer) {
        quizDao.insert(answer)
    }

    override fun getQuestionAndAllAnswers() = allQuestionsAndAllAnswers

    override suspend fun deleteQuestions() {
        quizDao.clearQuestions()
    }
}