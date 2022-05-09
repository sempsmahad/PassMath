/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kh69.passmath.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.kh69.passmath.data.Question

import com.kh69.passmath.data.source.QuestionsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Concrete implementation of a data source as a db.
 */
class QuestionsLocalDataSource internal constructor(
    private val questionsDao: QuestionsDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : QuestionsDataSource {

    override fun observeQuestions(): LiveData<Result<List<Question>>> {
        return questionsDao.observeQuestions().map {
            Success(it)
        }
    }

    override fun observeQuestion(questionId: String): LiveData<Result<Question>> {
        return questionsDao.observeQuestionById(questionId).map {
            Success(it)
        }
    }

    override suspend fun refreshQuestion(questionId: String) {
        // NO-OP
    }

    override suspend fun refreshQuestions() {
        // NO-OP
    }

    override suspend fun getQuestions(): Result<List<Question>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(questionsDao.getQuestions())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getQuestion(questionId: String): Result<Question> = withContext(ioDispatcher) {
        try {
            val question = questionsDao.getQuestionById(questionId)
            if (question != null) {
                return@withContext Success(question)
            } else {
                return@withContext Error(Exception("Question not found!"))
            }
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }

    override suspend fun saveQuestion(question: Question) = withContext(ioDispatcher) {
        questionsDao.insertQuestion(question)
    }

    override suspend fun completeQuestion(question: Question) = withContext(ioDispatcher) {
        questionsDao.updateCompleted(question.id, true)
    }

    override suspend fun completeQuestion(questionId: String) {
        questionsDao.updateCompleted(questionId, true)
    }

    override suspend fun activateQuestion(question: Question) = withContext(ioDispatcher) {
        questionsDao.updateCompleted(question.id, false)
    }

    override suspend fun activateQuestion(questionId: String) {
        questionsDao.updateCompleted(questionId, false)
    }

    override suspend fun clearCompletedQuestions() = withContext<Unit>(ioDispatcher) {
        questionsDao.deleteCompletedQuestions()
    }

    override suspend fun deleteAllQuestions() = withContext(ioDispatcher) {
        questionsDao.deleteQuestions()
    }

    override suspend fun deleteQuestion(questionId: String) = withContext<Unit>(ioDispatcher) {
        questionsDao.deleteQuestionById(questionId)
    }
}
