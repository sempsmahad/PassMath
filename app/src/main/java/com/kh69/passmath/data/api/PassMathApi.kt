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
        @Query(ApiParameters.YEAR) name: String,
        @Query(ApiParameters.TOPIC) topic: String,
        @Query(ApiParameters.SECTION) section: String,
        @Query(ApiParameters.PAPER) paper: String,
        @Query(ApiParameters.PAGE) pageToLoad: Int,
        @Query(ApiParameters.LIMIT) pageSize: Int
    ): ApiPaginatedQuestions
}
