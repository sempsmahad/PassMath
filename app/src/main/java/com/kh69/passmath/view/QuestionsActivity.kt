package com.kh69.passmath.view

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.kh69.passmath.QuestionAdapter
import com.kh69.passmath.R
import com.kh69.passmath.data.Repository
import com.kh69.passmath.data.model.QuizState
import com.kh69.passmath.databinding.ActivityQuestionListBinding
import com.kh69.passmath.getViewModel
import com.kh69.passmath.viewmodel.MainViewModel
import com.kh69.passmath.viewmodel.QuestionsViewModel


class QuestionsActivity : AppCompatActivity() {

    private val viewModel by lazy { getViewModel { MainViewModel(Repository()) } }
    private val quizViewModel by lazy { getViewModel { QuestionsViewModel(Repository()) } }

    private lateinit var binding: ActivityQuestionListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_list)

        binding = ActivityQuestionListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvQuestionList.layoutManager = LinearLayoutManager(this)
        getQuestions()
    }


    private fun prepopulateQuestions() = viewModel.prepopulateQuestions()

    private fun getQuestions() {
        prepopulateQuestions()
        quizViewModel.getCurrentState().observe(this)
        {
            render(it)
        }

    }

    private fun render(state: QuizState) {
        when (state) {
//            is QuizState.EmptyState   -> renderEmptyState()
            is QuizState.DataState    -> renderDataState(state)
            is QuizState.LoadingState -> renderLoadingState()
        }
    }

    private fun renderLoadingState() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun renderDataState(quizState: QuizState.DataState) {

        binding.progressBar.visibility = View.GONE
        displayQuestionsView()
        binding.rvQuestionList.adapter = QuestionAdapter(quizState.data)
        Toast.makeText(this,""+quizState.data.size,Toast.LENGTH_SHORT).show()
    }

    private fun displayQuestionsView() {
        binding.postsTopBar.visibility = View.VISIBLE
        binding.rvQuestionList.visibility = View.VISIBLE

    }


}