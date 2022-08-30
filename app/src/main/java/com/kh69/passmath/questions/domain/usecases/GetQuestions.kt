package com.kh69.passmath.questions.domain.usecases

import com.kh69.passmath.common.domain.repositories.QuestionRepository
import javax.inject.Inject


class GetQuestions @Inject constructor(private val questionRepository: QuestionRepository) {
    operator fun invoke() = questionRepository.getQuestions()
        .filter { it.isNotEmpty() }
}