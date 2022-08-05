package com.kh69.passmath.features.question.domain.usecase

import com.kh69.passmath.features.question.domain.model.Question
import com.kh69.passmath.features.question.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow

class GetQuestions(
    private val repository: QuestionRepository
) {
    operator fun invoke(

    ): Flow<List<Question>> {
        return repository.getQuestions()
    }
}
