package com.kh69.passmath.search.domain.model

import java.util.*

data class SearchParameters(
    val year: String,
    val topic: String,
    val section: String,
    val paper: String
) {
    val uppercaseTopic get() = topic.toUpperCase(Locale.ROOT)
    val uppercaseSection get() = section.toUpperCase(Locale.ROOT)
}