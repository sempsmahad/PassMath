package com.kh69.passmath.questions.presentation

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.kh69.passmath.R
import com.kh69.passmath.common.presentation.Event
import com.kh69.passmath.common.presentation.model.UIQuestion
import com.kh69.passmath.common.utils.ViewAnimation
import com.kh69.passmath.databinding.FragmentQuizBinding
import dagger.hilt.android.AndroidEntryPoint
import katex.hourglass.`in`.mathlib.MathView

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private val viewModel: QuizFragmentViewModel by viewModels()
    private val binding get() = _binding!!

    private var _binding: FragmentQuizBinding? = null

    companion object {
        const val MAX_QUESTIONS = 4
    }

    private var currentQtn = 1
    val answerIsVisible = booleanArrayOf(false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        requestInitialQuestionsList()
    }

    private fun setupUI() {
        binding.lytBack.setOnClickListener { requestPreviousQuestion() }
        binding.lytNext.setOnClickListener { requestNextQuestion() }
        observeViewStateUpdates()
    }


    private fun populateCard(question: UIQuestion) {
        removeZoomControls(arrayOf(binding.questionCard.kvQuestion, binding.questionCard.kvAnswer))
        binding.questionCard.kvQuestion.setDisplayText(question.katex_question)
        binding.questionCard.kvAnswer.setDisplayText(question.katex_answer)
        binding.questionCard.showAnswer.setOnClickListener {
            answerIsVisible[0] = !answerIsVisible[0]
            toggleAnswerVisibility(
                arrayOf(binding.questionCard.imageToBlur, binding.questionCard.kvAnswer),
                answerIsVisible[0],
                binding.questionCard.showAnswer
            )
        }
    }

    private fun removeZoomControls(mathViews: Array<MathView>) {
        for (mathView in mathViews) {
            mathView.settings.builtInZoomControls = true
            mathView.settings.displayZoomControls = false
        }
    }

    private fun toggleAnswerVisibility(
        views: Array<View>,
        ansVisible: Boolean,
        showAnswerBtn: ImageButton
    ) {
        if (ansVisible) {
            showAnswerBtn.setColorFilter(
                resources.getColor(R.color.light_green_600),
                PorterDuff.Mode.SRC_IN
            )
        } else {
            showAnswerBtn.setColorFilter(
                resources.getColor(R.color.grey_20),
                PorterDuff.Mode.SRC_IN
            )
        }
        for (view in views) {
            if (view.visibility == View.VISIBLE) {
                view.visibility = View.GONE
            } else {
                view.visibility = View.VISIBLE
            }
        }
    }

    private fun requestMoreQuestions() {
        viewModel.onEvent(QuizEvent.RequestMoreQuestions)
    }

    private fun observeViewStateUpdates() {
        viewModel.state.observe(viewLifecycleOwner) {
            updateScreenState(it)
        }
    }

    private fun updateScreenState(state: QuizViewState) {
        binding.progressBar.isVisible = state.loading
        populateCard(state.questions[state.currentQuestionIndex - 1])
        updateCurrentProgress(state.currentQuestionIndex)
        handleNoMoreQuestions(state.noMoreQuestions)
        handleFailures(state.failure)
    }

    private fun updateCurrentProgress(progress: Int) {
        val str_progress =
            String.format(getString(R.string.question_of), progress, MAX_QUESTIONS)
        binding.tvSteps.text = str_progress
        binding.status.text = str_progress
        ViewAnimation.fadeOutIn(binding.status)
    }

    private fun handleNoMoreQuestions(noMoreQuestionsNearby: Boolean) {
        // Show a warning message and a prompt for the user to try a different
        // distance or postcode
    }

    private fun handleFailures(failure: Event<Throwable>?) {
        val unhandledFailure = failure?.getContentIfNotHandled() ?: return

        val fallbackMessage = getString(R.string.an_error_occurred)

        val snackbarMessage = if (unhandledFailure.message.isNullOrEmpty()) {
            fallbackMessage
        } else {
            unhandledFailure.message!!
        }
        if (snackbarMessage.isNotEmpty()) {
            Snackbar.make(requireView(), snackbarMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun requestInitialQuestionsList() {
        viewModel.onEvent(QuizEvent.RequestInitialQuestionsList)
    }

    private fun requestNextQuestion() {
        viewModel.onEvent(QuizEvent.GoToNextQuestion)
    }

    private fun requestPreviousQuestion() {
        viewModel.onEvent(QuizEvent.GoToPreviousQuestion)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}