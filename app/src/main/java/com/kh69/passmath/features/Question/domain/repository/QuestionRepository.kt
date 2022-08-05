package com.kh69.passmath.features.question.domain.repository

import com.kh69.passmath.features.question.domain.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {

    fun getQuestions(): Flow<List<Question>>

    suspend fun getQuestionById(id: String): Question?

    suspend fun insertQuestion(question: Question)

    suspend fun deleteQuestion(question: Question)
}