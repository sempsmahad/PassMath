package com.kh69.passmath.domain.model.pagination

import com.kh69.passmath.data.cache.Question

data class PaginatedQuestions {
    val questions: List<Question>,
    val pagination: Pagination
}