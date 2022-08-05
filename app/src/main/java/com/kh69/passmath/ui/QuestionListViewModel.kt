package com.kh69.passmath.ui

import androidx.lifecycle.ViewModel
import com.kh69.passmath.data.source.QtnRepositoryImpl


class QuestionListViewModel constructor(repository: QtnRepositoryImpl) : ViewModel() {
    val questionListLiveData = repository.getQuestions()
}
