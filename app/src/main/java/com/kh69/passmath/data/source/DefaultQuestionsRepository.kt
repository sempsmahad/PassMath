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
package com.kh69.passmath.data.source

import androidx.lifecycle.LiveData
import com.kh69.passmath.data.Question
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.kh69.passmath.data.Result
import com.kh69.passmath.data.Result.*
import com.kh69.passmath.util.wrapEspressoIdlingResource

/**
 * Default implementation of [QuestionsRepository]. Single entry point for managing questions' data.
 */
class DefaultQuestionsRepository(
    private val questionsRemoteDataSource: QuestionsDataSource,
    private val questionsLocalDataSource: QuestionsDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : QuestionsRepository {

    override suspend fun getQuestions(forceUpdate: Boolean): Result<List<Question>> {
        // Set app as busy while this function executes.
        wrapEspressoIdlingResource {

            if (forceUpdate) {
                try {
                    updateQuestionsFromRemoteDataSource()
                } catch (ex: Exception) {
                    return Error(ex)
                }
            }
            return questionsLocalDataSource.getQuestions()
        }
    }

    override suspend fun refreshQuestions() {
        updateQuestionsFromRemoteDataSource()
    }

    override fun observeQuestions(): LiveData<Result<List<Question>>> {
        return questionsLocalDataSource.observeQuestions()
    }

    override suspend fun refreshQuestion(questionId: String) {
        updateQuestionFromRemoteDataSource(questionId)
    }

    private suspend fun updateQuestionsFromRemoteDataSource() {
        val remoteQuestions = questionsRemoteDataSource.getQuestions()

        if (remoteQuestions is Success) {
            // Real apps might want to do a proper sync, deleting, modifying or adding each question.
            questionsLocalDataSource.deleteAllQuestions()
            remoteQuestions.data.forEach { question ->
                questionsLocalDataSource.saveQuestion(question)
            }
        } else if (remoteQuestions is Error) {
            throw remoteQuestions.exception
        }
    }

    override fun observeQuestion(questionId: String): LiveData<Result<Question>> {
        return questionsLocalDataSource.observeQuestion(questionId)
    }

    private suspend fun updateQuestionFromRemoteDataSource(questionId: String) {
        val remoteQuestion = questionsRemoteDataSource.getQuestion(questionId)

        if (remoteQuestion is Success) {
            questionsLocalDataSource.saveQuestion(remoteQuestion.data)
        }
    }

    /**
     * Relies on [getQuestions] to fetch data and picks the question with the same ID.
     */
    override suspend fun getQuestion(questionId: String, forceUpdate: Boolean): Result<Question> {
        // Set app as busy while this function executes.
        wrapEspressoIdlingResource {
            if (forceUpdate) {
                updateQuestionFromRemoteDataSource(questionId)
            }
            return questionsLocalDataSource.getQuestion(questionId)
        }
    }

    override suspend fun saveQuestion(question: Question) {
        coroutineScope {
            launch { questionsRemoteDataSource.saveQuestion(question) }
            launch { questionsLocalDataSource.saveQuestion(question) }
        }
    }


    override suspend fun deleteAllQuestions() {
        withContext(ioDispatcher) {
            coroutineScope {
                launch { questionsRemoteDataSource.deleteAllQuestions() }
                launch { questionsLocalDataSource.deleteAllQuestions() }
            }
        }
    }

    override suspend fun deleteQuestion(questionId: String) {
        coroutineScope {
            launch { questionsRemoteDataSource.deleteQuestion(questionId) }
            launch { questionsLocalDataSource.deleteQuestion(questionId) }
        }
    }

    private suspend fun getQuestionWithId(id: String): Result<Question> {
        return questionsLocalDataSource.getQuestion(id)
    }
}
