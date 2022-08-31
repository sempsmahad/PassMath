package com.kh69.passmath.questions.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.kh69.passmath.R
import com.kh69.passmath.common.presentation.Event
import com.kh69.passmath.databinding.FragmentQuizBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment()  {

    private val viewModel: QuizFragmentViewModel by viewModels()
    private val binding get() = _binding!!

    private var _binding: FragmentQuizBinding? = null

    companion object {
        const val MAX_QUESTIONS = 4
    }

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
//        TODO("Not yet implemented")
    }

    private fun requestMoreQuestions() {
        viewModel.onEvent(QuizEvent.RequestMoreQuestions)
    }

    private fun observeViewStateUpdates(adapter: QuizAdapter) {
        viewModel.state.observe(viewLifecycleOwner) {
            updateScreenState(it, adapter)
        }
    }

    private fun updateScreenState(state: QuizViewState, adapter: QuizAdapter) {
        binding.progressBar.isVisible = state.loading
        adapter.submitList(state.questions)
        handleNoMoreQuestions(state.noMoreQuestions)
        handleFailures(state.failure)
    }

    private fun handleNoMoreAnimalsNearby(noMoreQuestionsNearby: Boolean) {
        // Show a warning message and a prompt for the user to try a different
        // distance or postcode
    }

    private fun handleFailures(failure: Event<Throwable>?) {
        val unhandledFailure = failure?.getContentIfNotHandled() ?: return

        val fallbackMessage = getString(R.string.an_error_occurred)

        val snackbarMessage = if (unhandledFailure.message.isNullOrEmpty()) {
            fallbackMessage
        }
        else {
            unhandledFailure.message!! }
        if (snackbarMessage.isNotEmpty()) {
            Snackbar.make(requireView(), snackbarMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun requestInitialQuestionsList() {
        viewModel.onEvent(QuizEvent.RequestInitialQuestionsList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}