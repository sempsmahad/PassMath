package com.kh69.passmath.common.data.cache.model.cachedquestion

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kh69.passmath.common.domain.model.question.Paper
import com.kh69.passmath.common.domain.model.question.Question
import com.kh69.passmath.common.domain.model.question.Section

@Entity(
    tableName = "questions",
    indices = [Index("year")]
)
data class CachedQuestion(
    @PrimaryKey
    val questionId: String,
    val text: String,
    val year: Int,
    val paper: String,
    val section: String,
    val topic: String,
    val answer: String,
    val katex_question: String,
    val katex_answer: String,
    val edited: Boolean
) {
    companion object {
        fun fromDomain(domainModel: Question): CachedQuestion {

            return CachedQuestion(
                questionId = domainModel.id,
                text = domainModel.text,
                year = domainModel.year,
                paper = domainModel.paper.toString(),
                section = domainModel.section.toString(),
                topic = domainModel.topic,
                answer = domainModel.answer,
                katex_question = domainModel.katex_question,
                katex_answer = domainModel.katex_answer,
                edited = domainModel.edited
            )
        }
    }

    fun toDomain(
    ): Question {
        return Question(
            id = questionId,
            text = text,
            year = year,
            paper = Paper.valueOf(paper),
            section = Section.valueOf(section),
            topic = topic,
            answer = answer,
            katex_question = katex_question,
            katex_answer = katex_answer,
            edited = edited
        )
    }

}
