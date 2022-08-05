package com.kh69.passmath.features.question.domain.usecase

import com.kh69.passmath.features.question.domain.model.Question
import com.kh69.passmath.features.question.domain.repository.QuestionRepository

class GetQuestion(
    private val repository: QuestionRepository
) {

    suspend operator fun invoke(id: String): Question? {
        return repository.getQuestionById(id)
    }
}