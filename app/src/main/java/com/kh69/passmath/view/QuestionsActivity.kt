package com.kh69.passmath.view

import androidx.appcompat.app.AppCompatActivity
import com.kh69.passmath.data.Repository
import com.kh69.passmath.getViewModel
import com.kh69.passmath.viewmodel.MainViewModel

class QuestionsActivity : AppCompatActivity()  {

    private val viewModel by lazy { getViewModel { MainViewModel(Repository()) } }
    private fun prepopulateQuestions() = viewModel.prepopulateQuestions()

    private fun getQuestions(){
        prepopulateQuestions()

    }


}