package com.kh69.passmath.legacy.data.source.remote

import androidx.lifecycle.LiveData
import com.kh69.passmath.legacy.data.Question
import retrofit2.http.*

/**
 * REST API access points
 */
interface MathService {

//    @GET("questions")
//    fun getQuestions(): LiveData<ApiResponse<Response>>

    @GET("questions")
    fun getQuestions(): LiveData<ApiResponse<List<Question>>>

//    @GET("questions")
//    fun getQuestions(): List<Question>

//    @GET("questions/{id}")
//    fun getQuestion(@Path("id") id: String?): Question

    @GET("questions/{id}")
    fun getQuestion(@Path("id") id: String?): LiveData<ApiResponse<Question>>

//    @POST("questions")
//    fun createQuestion(@Body question: Question?): LiveData<ApiResponse<Question>>

    @POST("questions")
    fun createQuestion(@Body question: Question?): Question

    @PATCH("questions/{id}")
    fun updateQuestion(
        @Path("id") id: String?,
        @Body question: Question?
    ): LiveData<ApiResponse<Question>>

    @DELETE("questions/{id}")
    fun deleteQuestion(@Path("id") id: String?): LiveData<ApiResponse<Question>>

    @DELETE("questions")
    fun deleteQuestions(): LiveData<ApiResponse<List<Question>>>

//    @DELETE("questions/{id}")
//    fun deleteQuestion(@Path("id") id: String?): Question

//    @DELETE("questions")
//    fun deleteQuestions(): Question

}
