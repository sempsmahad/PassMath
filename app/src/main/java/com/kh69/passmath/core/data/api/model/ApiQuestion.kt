package com.kh69.passmath.core.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiQuestion(
    @field:Json(name = "_id") val id: String,
    @field:Json(name = "qtn_text") val text: String?,
    @field:Json(name = "year") val year: Int?,
    @field:Json(name = "paper") val paper: Int?,
    @field:Json(name = "section") val section:String?,
    @field:Json(name = "topic") val topic: String?,
    @field:Json(name = "answer") val answer: String?,
    @field:Json(name = "katex_question") val katex_question: String?,
    @field:Json(name = "katex_answer") val katex_answer: String?,
    @field:Json(name = "edited") val edited: String?
)
