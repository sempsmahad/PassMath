package com.kh69.passmath.data.api

import com.kh69.passmath.data.api.model.ApiPaginatedQuestions
import retrofit2.http.GET
import retrofit2.http.Query

interface PassMathApi {

    @GET(ApiConstants.ANIMALS_ENDPOINT)
    suspend fun getNearbyAnimals(
        @Query(ApiParameters.PAGE) pageToLoad: Int,
        @Query(ApiParameters.LIMIT) pageSize: Int,
        @Query(ApiParameters.LOCATION) postcode: String,
        @Query(ApiParameters.DISTANCE) maxDistance: Int
    ): ApiPaginatedQuestions

    @GET(ApiConstants.ANIMALS_ENDPOINT)
    suspend fun searchAnimalsBy(
        @Query(ApiParameters.NAME) name: String,
        @Query(ApiParameters.AGE) age: String,
        @Query(ApiParameters.TYPE) type: String,
        @Query(ApiParameters.PAGE) pageToLoad: Int,
        @Query(ApiParameters.LIMIT) pageSize: Int,
        @Query(ApiParameters.LOCATION) postcode: String,
        @Query(ApiParameters.DISTANCE) maxDistance: Int
    ): ApiPaginatedQuestions
}
