package com.kh69.passmath.common.data.api.model.mappers

import com.kh69.passmath.common.data.api.model.ApiQuestion
import com.kh69.passmath.common.domain.model.question.Paper
import com.kh69.passmath.common.domain.model.question.Question
import com.kh69.passmath.common.domain.model.question.Section
import javax.inject.Inject

class ApiQuestionMapper @Inject constructor(

) : ApiMapper<ApiQuestion, Question> {

    override fun mapToDomain(apiEntity: ApiQuestion): Question {
        return Question(
            id = apiEntity.id ?: throw MappingException("Question ID cannot be null"),
            text = apiEntity.text.orEmpty(),
            year = apiEntity.year.orNoYear(),
            paper = parsePaper(apiEntity.paper),
            section = parseSection(apiEntity.section),
            topic = apiEntity.topic.orEmpty(),
            answer = apiEntity.answer.orEmpty(),
            katex_question = apiEntity.katex_question.orEmpty(),
            katex_answer = apiEntity.katex_answer.orEmpty(),
            edited = apiEntity?.edited ?: true
        )
    }

    private fun parsePaper(paper: Int?): Paper {
        return when (paper) {
            1    -> Paper.PAPER_ONE
            2    -> Paper.PAPER_TWO
            else -> Paper.UNKNOWN
        }
    }

    private fun parseSection(section: String?): Section {
        return when (section) {
            "A"  -> Section.SECTION_A
            "B"  -> Section.SECTION_B
            else -> Section.UNKNOWN
        }
    }
}

/** Returns the string if it is not `null`, or the empty string otherwise. */
public inline fun Int?.orNoYear(): Int = this ?: 1900
