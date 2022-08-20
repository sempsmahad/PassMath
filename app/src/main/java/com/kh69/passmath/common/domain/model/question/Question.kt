package com.kh69.passmath.common.domain.model.question


data class Question(
    val id: String,
    val text: String,
    val year: Int,
    val paper: Paper,
    val section: Section,
    val topic: String,
    val answer: String,
    val katex_question: String,
    val katex_answer: String,
    val edited: Boolean
)