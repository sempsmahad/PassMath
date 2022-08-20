package com.kh69.passmath.legacy.ui

import androidx.lifecycle.ViewModel
import com.kh69.passmath.legacy.data.source.QtnRepository


class QuestionListViewModel constructor(repository: QtnRepository) : ViewModel() {
    val questionListLiveData = repository.getQuestions()
}
