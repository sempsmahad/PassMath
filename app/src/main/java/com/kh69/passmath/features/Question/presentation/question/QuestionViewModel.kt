package com.kh69.passmath.features.question.presentation.question

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kh69.passmath.features.question.domain.usecase.QuestionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val questionUseCases: QuestionUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(QuestionState())
    val stateFlow = _stateFlow as StateFlow<QuestionState>

    private var currentQuestionId: String? = null
    private var getQuestionsJob: Job? = null
    
    init {
//        getQuestions()
        savedStateHandle.get<String>("questionId")?.let { question_id ->
            if(question_id.isNotEmpty()) {
                viewModelScope.launch {
                    questionUseCases.getQuestion(question_id)?.also { question ->
                        currentQuestionId = question.id
                        _stateFlow.value = stateFlow.value.copy(

                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: QuestionEvent) {
        when (event) {
            is QuestionEvent.ViewAnswer -> {
                _stateFlow.value = stateFlow.value.copy(
                    answerIsVisible = event.isVisible
                )
            }

            is QuestionEvent.switch_to_previous -> {
                var index = stateFlow.value.questionIndex
                _stateFlow.value = stateFlow.value.copy(
                    questionIndex = if (index == 1) 1 else index-1
                )
            }

            is QuestionEvent.switch_to_next -> {
                var index = stateFlow.value.questionIndex
                _stateFlow.value = stateFlow.value.copy(
                    questionIndex = if (index == 4) 4 else index+1
                )
            }
        }
    }

//    private fun getNotes(noteOrder: NoteOrder) {
//        getNotesJob?.cancel()
//        getNotesJob = questionUseCases.getNotes(noteOrder)
//            .onEach { notes ->
//                _state.value = state.value.copy(
//                    notes = notes,
//                    noteOrder = noteOrder
//                )
//            }
//            .launchIn(viewModelScope)
//    }

    private fun getQuestions() {
        getQuestionsJob?.cancel()
        getQuestionsJob = questionUseCases.getQuestions()
            .onEach { questions ->
                _stateFlow.value = stateFlow.value.copy(
                    questions = questions,
                    questionIndex =  if (questions.isNotEmpty()) 1 else 0,
                    number_of_questions = 4
                )
            }
            .launchIn(viewModelScope)
    }
}