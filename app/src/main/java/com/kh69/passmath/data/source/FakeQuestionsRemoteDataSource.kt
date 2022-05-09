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
import androidx.lifecycle.MutableLiveData
import com.kh69.passmath.data.Question
import java.util.LinkedHashMap

/**
 * Implementation of a remote data source with static access to the data for easy testing.
 */
object FakeQuestionsRemoteDataSource : QuestionsDataSource {

    private var TASKS_SERVICE_DATA: LinkedHashMap<String, Question> = LinkedHashMap()

    private val observableQuestions = MutableLiveData<Result<List<Question>>>()

    override suspend fun refreshQuestions() {
        observableQuestions.postValue(getQuestions())
    }

    override suspend fun refreshQuestion(questionId: String) {
        refreshQuestions()
    }

    override fun observeQuestions(): LiveData<Result<List<Question>>> {
        return observableQuestions
    }

    override fun observeQuestion(questionId: String): LiveData<Result<Question>> {
        return observableQuestions.map { questions ->
            when (questions) {
                is Result.Loading -> Result.Loading
                is Error -> Error(questions.exception)
                is Success -> {
                    val question = questions.data.firstOrNull() { it.id == questionId }
                        ?: return@map Error(Exception("Not found"))
                    Success(question)
                }
            }
        }
    }

    override suspend fun getQuestion(questionId: String): Result<Question> {
        TASKS_SERVICE_DATA[questionId]?.let {
            return Success(it)
        }
        return Error(Exception("Could not find question"))
    }

    override suspend fun getQuestions(): Result<List<Question>> {
        return Success(TASKS_SERVICE_DATA.values.toList())
    }

    override suspend fun saveQuestion(question: Question) {
        TASKS_SERVICE_DATA[question.id] = question
    }

    override suspend fun completeQuestion(question: Question) {
        val completedQuestion = Question(question.title, question.description, true, question.id)
        TASKS_SERVICE_DATA[question.id] = completedQuestion
    }

    override suspend fun completeQuestion(questionId: String) {
        // Not required for the remote data source.
    }

    override suspend fun activateQuestion(question: Question) {
        val activeQuestion = Question(question.title, question.description, false, question.id)
        TASKS_SERVICE_DATA[question.id] = activeQuestion
    }

    override suspend fun activateQuestion(questionId: String) {
        // Not required for the remote data source.
    }

    override suspend fun clearCompletedQuestions() {
        TASKS_SERVICE_DATA = TASKS_SERVICE_DATA.filterValues {
            !it.isCompleted
        } as LinkedHashMap<String, Question>
    }

    override suspend fun deleteQuestion(questionId: String) {
        TASKS_SERVICE_DATA.remove(questionId)
        refreshQuestions()
    }

    override suspend fun deleteAllQuestions() {
        TASKS_SERVICE_DATA.clear()
        refreshQuestions()
    }
}
