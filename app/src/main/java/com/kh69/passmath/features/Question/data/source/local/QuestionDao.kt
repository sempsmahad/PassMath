package com.kh69.passmath.features.question.data.source.local

import androidx.room.*
import com.kh69.passmath.features.question.domain.model.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Query("SELECT * FROM Questions")
    fun getQuestions(): Flow<List<Question>>

    @Query("SELECT * FROM Questions WHERE questionId = :id")
    suspend fun getQuestionById(id: String): Question?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Delete
    suspend fun deleteQuestion(question: Question)
}