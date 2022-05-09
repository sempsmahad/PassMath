package com.kh69.passmath.data.source

import androidx.lifecycle.LiveData
import com.kh69.passmath.data.Question

/**
 * Interface to the data layer.
 */
interface QuestionsRepository {

    fun observeQuestions(): LiveData<Result<List<Question>>>

    suspend fun getQuestions(forceUpdate: Boolean = false): Result<List<Question>>

    suspend fun refreshQuestions()

    fun observeQuestion(questionId: String): LiveData<Result<Question>>

    suspend fun getQuestion(questionId: String, forceUpdate: Boolean = false): Result<Question>

    suspend fun refreshQuestion(questionId: String)

    suspend fun saveQuestion(question: Question)

    suspend fun completeQuestion(question: Question)

    suspend fun completeQuestion(questionId: String)

    suspend fun activateQuestion(question: Question)

    suspend fun activateQuestion(questionId: String)

    suspend fun clearCompletedQuestions()

    suspend fun deleteAllQuestions()

    suspend fun deleteQuestion(questionId: String)
}
