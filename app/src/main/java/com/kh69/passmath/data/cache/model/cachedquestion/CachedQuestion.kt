package com.kh69.passmath.data.cache.model.cachedquestion

import com.kh69.passmath.data.cache.Question


data class CachedQuestion(
    val questionId: String,
    val text: String,
    val year: String,
    val paper: String,
    val section: String,
    val topic: String,
    val answer: String,
    val katex_question: String,
    val katex_answer: String,
    val edited: String
) {

    companion object {
        fun fromDomain(question: Question): CachedQuestion {
            return CachedQuestion(
                question.id,
                question.text,
                question.year.toString(),
                question.paper.toString(),
                question.section,
                question.topic,
                question.answer,
                question.katex_question,
                question.katex_answer,
                question.edited.toString()
            )
        }
    }

    fun toDomain(photos: List<CachedPhoto>, videos: List<CachedVideo>, tags: List<CachedTag>): Animal {
        return Animal(
            animalId,
            name,
            type,
            Media(
                photos = photos.map { it.toDomain() },
                videos = videos.map { it.toDomain() }
            ),
            tags.map { it.tag },
            AdoptionStatus.valueOf(adoptionStatus),
            DateTimeUtils.parse(publishedAt)
        )
    }
}