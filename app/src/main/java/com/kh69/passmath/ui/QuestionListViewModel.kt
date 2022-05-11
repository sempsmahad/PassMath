package com.kh69.passmath.ui

import androidx.lifecycle.ViewModel
import com.kh69.passmath.data.source.QtnRepository


class QuestionListViewModel constructor(repository: QtnRepository) : ViewModel() {
    val questionListLiveData = repository.getQuestions()
}
