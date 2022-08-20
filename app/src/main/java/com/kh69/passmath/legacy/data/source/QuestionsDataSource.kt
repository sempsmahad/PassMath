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
package com.kh69.passmath.legacy.data.source

import androidx.lifecycle.LiveData
import com.kh69.passmath.legacy.data.Question
import com.kh69.passmath.legacy.data.Result


/**
 * Main entry point for accessing questions data.
 */
interface QuestionsDataSource {

    fun observeQuestions(): LiveData<Result<List<Question>>>

    suspend fun getQuestions(): Result<List<Question>>

    suspend fun refreshQuestions()

    fun observeQuestion(questionId: String): LiveData<Result<Question>>

    suspend fun getQuestion(questionId: String): Result<Question>

    suspend fun refreshQuestion(questionId: String)

    suspend fun saveQuestion(question: Question)

    suspend fun deleteAllQuestions()

    suspend fun deleteQuestion(questionId: String)
}
