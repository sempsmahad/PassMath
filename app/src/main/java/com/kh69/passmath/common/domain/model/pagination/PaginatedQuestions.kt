package com.kh69.passmath.common.domain.model.pagination

import com.kh69.passmath.legacy.data.Question


data class PaginatedQuestions(
    val questions: List<Question>,
    val pagination: Pagination
)