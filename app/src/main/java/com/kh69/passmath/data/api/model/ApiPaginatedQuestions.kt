package com.kh69.passmath.data.api.model

import com.kh69.passmath.core.data.api.model.ApiQuestion
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class ApiPaginatedQuestions {
    @JsonClass(generateAdapter = true)
    data class ApiPaginatedQuestions(
        @field:Json(name = "questions") val questions: List<ApiQuestion>?,
        @field:Json(name = "pagination") val pagination: ApiPagination?
    )

    @JsonClass(generateAdapter = true)
    data class ApiPagination(
        @field:Json(name = "count_per_page") val countPerPage: Int?,
        @field:Json(name = "total_count") val totalCount: Int?,
        @field:Json(name = "current_page") val currentPage: Int?,
        @field:Json(name = "total_pages") val totalPages: Int?
    )
}