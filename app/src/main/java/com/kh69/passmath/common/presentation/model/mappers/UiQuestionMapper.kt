package com.kh69.passmath.common.presentation.model.mappers

import com.kh69.passmath.common.domain.model.question.Question
import com.kh69.passmath.common.presentation.model.UIQuestion
import javax.inject.Inject

class UiQuestionMapper @Inject constructor() : UiMapper<Question, UIQuestion> {

    override fun mapToView(input: Question): UIQuestion {
        return UIQuestion(
            id = input.id,
            katex_question = input.katex_question,
            katex_answer = input.katex_answer
        )
    }
}
