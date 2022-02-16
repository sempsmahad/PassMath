package com.kh69.passmath.search.domain.model

import com.kh69.passmath.data.cache.Question

data class SearchResults(
    val questions: List<Question>,
    val searchParameters: SearchParameters
)