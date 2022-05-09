package com.kh69.passmath.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kh69.passmath.data.Question


@Dao
interface QuestionsDao {

    /**
     * Observes list of questions.
     *
     * @return all questions.
     */
    @Query("SELECT * FROM Questions")
    fun observeQuestions(): LiveData<List<Question>>

    /**
     * Observes a single question.
     *
     * @param questionId the question id.
     * @return the question with questionId.
     */
    @Query("SELECT * FROM Questions WHERE questionId = :questionId")
    fun observeQuestionById(questionId: String): LiveData<Question>

    /**
     * Select all questions from the questions table.
     *
     * @return all questions.
     */
    @Query("SELECT * FROM Questions")
    suspend fun getQuestions(): List<Question>

    /**
     * Select a question by id.
     *
     * @param questionId the question id.
     * @return the question with questionId.
     */
    @Query("SELECT * FROM Questions WHERE questionId = :questionId")
    suspend fun getQuestionById(questionId: String): Question?

    /**
     * Insert a question in the database. If the question already exists, replace it.
     *
     * @param question the question to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    /**
     * Update a question.
     *
     * @param question question to be updated
     * @return the number of questions updated. This should always be 1.
     */
    @Update
    suspend fun updateQuestion(question: Question): Int

    /**
     * Delete a question by id.
     *
     * @return the number of questions deleted. This should always be 1.
     */
    @Query("DELETE FROM Questions WHERE questionId = :questionId")
    suspend fun deleteQuestionById(questionId: String): Int

    /**
     * Delete all questions.
     */
    @Query("DELETE FROM Questions")
    suspend fun deleteQuestions()

}