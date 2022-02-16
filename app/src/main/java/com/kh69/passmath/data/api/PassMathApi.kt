package com.kh69.passmath.data.api

import com.kh69.passmath.data.api.model.ApiPaginatedQuestions
import retrofit2.http.GET
import retrofit2.http.Query

interface PassMathApi {

    @GET(ApiConstants.QUESTIONS_ENDPOINT)
    suspend fun getQuestions(
        @Query(ApiParameters.PAGE) pageToLoad: Int,
        @Query(ApiParameters.LIMIT) pageSize: Int
    ): ApiPaginatedQuestions

    @GET(ApiConstants.QUESTIONS_ENDPOINT)
    suspend fun searchQuestions(
        @Query(ApiParameters.NAME) name: String,
        @Query(ApiParameters.AGE) age: String,
        @Query(ApiParameters.TYPE) type: String,
        @Query(ApiParameters.PAGE) pageToLoad: Int,
        @Query(ApiParameters.LIMIT) pageSize: Int
    ): ApiPaginatedQuestions
}
