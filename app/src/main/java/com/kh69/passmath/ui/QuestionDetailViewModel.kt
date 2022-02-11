package com.kh69.passmath.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.kh69.passmath.data.cache.Question
import com.kh69.passmath.data.cache.QuestionRepository
import org.w3c.dom.Node
import java.util.*

class QuestionDetailViewModel() : ViewModel() {

    private val questionRepository = QuestionRepository.get()
    private val questionIdLiveData = MutableLiveData<UUID>()


    var questionLiveData: LiveData<Question?> =
        Transformations.switchMap(questionIdLiveData) { questionId ->
            questionRepository.getQuestion(questionId)
        }

    fun loadQuestion(questionId: UUID) {
        questionIdLiveData.value = questionId
    }

    fun saveQuestion(question: Question) {
        questionRepository.updateQuestion(question)
    }

    fun addQuestion(question: Question) {
        questionRepository.addQuestion(question)
    }


}