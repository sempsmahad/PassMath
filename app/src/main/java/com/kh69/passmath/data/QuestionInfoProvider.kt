package com.kh69.passmath.data

import com.kh69.passmath.Answer
import com.kh69.passmath.data.cache.Question
import com.raywenderlich.android.droidquiz.data.model.Answer
import com.raywenderlich.android.droidquiz.data.model.Question

object QuestionInfoProvider {

    val questionList = initQuestionList()
    val answerList = initAnswersList()

    private fun initQuestionList(): MutableList<Question> {
        val questions = mutableListOf<Question>()
        questions.add(
            Question(
                1,
                "Which of the following languages is not commonly used to develop Android Apps"
            )
        )
        questions.add(
            Question(
                2,
                "What is the meaning of life?"
            )
        )
        return questions
    }

}