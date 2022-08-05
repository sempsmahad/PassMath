package com.kh69.passmath.features.question.data.repository

import com.kh69.passmath.features.question.data.source.local.QuestionDao
import com.kh69.passmath.features.question.domain.model.Question
import com.kh69.passmath.features.question.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow


class QuestionRepositoryImpl(
    private val dao: QuestionDao
) : QuestionRepository {

    override fun getQuestions(): Flow<List<Question>> {
        return dao.getQuestions()
    }

    override suspend fun getQuestionById(id: String): Question? {
        return dao.getQuestionById(id)
    }

    override suspend fun insertQuestion(question: Question) {
        dao.insertQuestion(question)
    }

    override suspend fun deleteQuestion(question: Question) {
        dao.deleteQuestion(question)
    }
}