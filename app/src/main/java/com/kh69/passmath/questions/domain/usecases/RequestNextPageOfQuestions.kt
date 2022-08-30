package com.kh69.passmath.questions.domain.usecases

import com.kh69.passmath.common.domain.model.NoMoreQuestionsException
import com.kh69.passmath.common.domain.model.pagination.Pagination
import com.kh69.passmath.common.domain.repositories.QuestionRepository
import javax.inject.Inject


class RequestNextPageOfQuestions @Inject constructor(private val questionRepository: QuestionRepository) {
    suspend operator fun invoke(
        pageToLoad: Int,
        pageSize: Int = Pagination.DEFAULT_PAGE_SIZE
    ): Pagination {
        val (questions, pagination) = questionRepository.requestMoreQuestions(pageToLoad, pageSize)

        if (questions.isEmpty()) {
            throw NoMoreQuestionsException("No more questions available :(")
        }

        questionRepository.storeQuestions(questions)

        return pagination
    }
}