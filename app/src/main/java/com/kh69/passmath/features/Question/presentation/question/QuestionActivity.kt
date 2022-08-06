package com.kh69.passmath.features.question.presentation.question

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kh69.passmath.extensions.collectLatestLifecycleFlow

class QuestionActivity:AppCompatActivity() {

    companion object {
        const val MAX_QUESTIONS = 4
    }

    lateinit var viewModel:QuestionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelProviderFactory = QuestionViewModelProviderFactory()
        collectLatestLifecycleFlow(viewModel.stateflow){

        }
    }

}