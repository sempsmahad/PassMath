package com.kh69.passmath.common.data.api

import com.kh69.passmath.common.data.api.model.ApiPaginatedQuestions
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionLoaderApi {

    @GET(ApiConstants.QUESTIONS_ENDPOINT)
    suspend fun getQuestions(
        @Query(ApiParameters.PAGE) pageToLoad: Int,
        @Query(ApiParameters.LIMIT) pageSize: Int
    ): ApiPaginatedQuestions

    @GET(ApiConstants.QUESTIONS_ENDPOINT)
    suspend fun searchQuestionsBy(
        @Query(ApiParameters.ID) id: String,
        @Query(ApiParameters.PAGE) pageToLoad: Int,
        @Query(ApiParameters.LIMIT) pageSize: Int
    ): ApiPaginatedQuestions
}
