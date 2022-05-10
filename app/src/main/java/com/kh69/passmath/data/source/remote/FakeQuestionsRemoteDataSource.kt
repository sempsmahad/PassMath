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
package com.kh69.passmath.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.kh69.passmath.api.*
import com.kh69.passmath.data.Question
import com.kh69.passmath.data.Resource
import java.util.LinkedHashMap
import com.kh69.passmath.data.Result
import com.kh69.passmath.data.Result.*
import com.kh69.passmath.data.source.QuestionsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementation of a remote data source with static access to the data for easy testing.
 */
class FakeQuestionsRemoteDataSource internal constructor(
    private val mathService: MathService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    QuestionsDataSource {

    private var TASKS_SERVICE_DATA: LinkedHashMap<String, Question> = LinkedHashMap()

    private val observableQuestions = MutableLiveData<Result<List<Question>>>()


    private val _liveData = MutableLiveData<Resource<Boolean>>()
    val liveData: LiveData<Resource<Boolean>> = _liveData


    override suspend fun refreshQuestions() {
        observableQuestions.postValue(getQuestions()!!)
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
                is Loading -> Loading
                is Error   -> Error(questions.exception)
                is Success -> {
                    val question = questions.data.firstOrNull() { it.id == questionId }
                        ?: return@map Error(Exception("Not found"))
                    Success(question)
                }
            }
        }
    }

    override suspend fun getQuestion(questionId: String): Result<Question> = withContext(ioDispatcher){
        mathService.getQuestion(questionId).let {
            return@withContext Success(it)
        }
//        return Error(Exception("Could not find question"))
    }

    override suspend fun getQuestions(): Result<List<Question>> = withContext(ioDispatcher) {
        return@withContext Success(mathService.getQuestions())
    }

    override suspend fun saveQuestion(question: Question) = withContext(ioDispatcher) {
        mathService.createQuestion(question)
        refreshQuestions()
    }

    override suspend fun deleteQuestion(questionId: String) = withContext(ioDispatcher) {
        mathService.deleteQuestion(questionId)
        refreshQuestions()
    }

    override suspend fun deleteAllQuestions() = withContext(ioDispatcher) {
        mathService.deleteQuestions()
        refreshQuestions()
    }
}
