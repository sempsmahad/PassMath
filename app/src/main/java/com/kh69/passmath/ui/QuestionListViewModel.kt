package com.kh69.passmath.ui

import androidx.lifecycle.ViewModel
import com.kh69.passmath.data.Question
import com.kh69.passmath.data.cache.QuestionRepository


class QuestionListViewModel : ViewModel() {

    fun addQuestion(question: Question) {
        questionRepository.addQuestion(question)
    }
    private val questionRepository = QuestionRepository.get()
    val questionListLiveData = questionRepository.getQuestions()
}
