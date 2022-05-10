/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.kh69.passmath.api

import androidx.lifecycle.LiveData
import com.kh69.passmath.data.Question
import retrofit2.Call
import retrofit2.http.*

/**
 * REST API access points
 */
interface MathService {
//
//    @GET("questions")
//    fun getQuestions(): LiveData<ApiResponse<List<Question>>>

    @GET("questions")
    fun getQuestions(): List<Question>

    @GET("questions/{id}")
    fun getQuestion(@Path("id") id: String?): Question


//    @POST("questions")
//    fun createQuestion(@Body question: Question?): LiveData<ApiResponse<Question>>

    @POST("questions")
    fun createQuestion(@Body question: Question?): Question

    @PATCH("questions/{id}")
    fun updateQuestion(
        @Path("id") id: String?,
        @Body question: Question?
    ): LiveData<ApiResponse<Question>>

//    @DELETE("questions/{id}")
//    fun deleteQuestion(@Path("id") id: String?): LiveData<ApiResponse<Question>>

    @DELETE("questions/{id}")
    fun deleteQuestion(@Path("id") id: String?): Question

    @DELETE("questions")
    fun deleteQuestions(): Question

}
