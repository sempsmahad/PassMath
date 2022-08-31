package com.kh69.passmath.questions.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kh69.logging.Logger
import com.kh69.passmath.common.domain.model.NetworkException
import com.kh69.passmath.common.domain.model.NetworkUnavailableException
import com.kh69.passmath.common.domain.model.NoMoreQuestionsException
import com.kh69.passmath.common.domain.model.pagination.Pagination
import com.kh69.passmath.common.domain.model.question.Question
import com.kh69.passmath.common.presentation.Event
import com.kh69.passmath.common.presentation.model.mappers.UiQuestionMapper
import com.kh69.passmath.common.utils.DispatchersProvider
import com.kh69.passmath.common.utils.createExceptionHandler
import com.kh69.passmath.questions.domain.usecases.GetQuestions
import com.kh69.passmath.questions.domain.usecases.RequestNextPageOfQuestions
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class QuizFragmentViewModel @Inject constructor(
    private val getQuestions: GetQuestions,
    private val requestNextPageOfQuestions: RequestNextPageOfQuestions,
    private val uiQuestionMapper: UiQuestionMapper,
    private val dispatchersProvider: DispatchersProvider,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    companion object {
        const val UI_PAGE_SIZE = Pagination.DEFAULT_PAGE_SIZE
    }

    val state: LiveData<QuizViewState> get() = _state
    var isLoadingMoreAnimals: Boolean = false
    var isLastPage = false

    private val _state = MutableLiveData<QuizViewState>()
    private var currentPage = 0

    init {
        _state.value = QuizViewState()
        subscribeToQuestionUpdates()
    }

    fun onEvent(event: QuizEvent) {
        when (event) {
            is QuizEvent.RequestInitialQuestionsList -> loadQuestions()
            is QuizEvent.RequestMoreQuestions -> loadNextQuestionPage()
        }
    }

    private fun subscribeToQuestionUpdates() {
        getQuestions()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onNewQuestionsList(it) },
                { onFailure(it) }
            )
            .addTo(compositeDisposable)
    }

    private fun onNewQuestionsList(questions: List<Question>) {
        Logger.d("Got more questions!")

        val questions = questions.map { uiQuestionMapper.mapToView(it) }

        val currentList = state.value!!.questions
        val newQuestions = questions.subtract(currentList.toSet())
        val updatedList = currentList + newQuestions

        _state.value = state.value!!.copy(loading = false, questions = updatedList)
    }

    private fun loadQuestions() {
        if (state.value!!.questions.isEmpty()) {
            loadNextQuestionPage()
        }
    }

    private fun loadNextQuestionPage() {
        isLoadingMoreAnimals = true

        val errorMessage = "Failed to fetch nearby animals"
        val exceptionHandler = viewModelScope.createExceptionHandler(errorMessage) { onFailure(it) }

        viewModelScope.launch(exceptionHandler) {
            val pagination = withContext(dispatchersProvider.io()) {
                Logger.d("Requesting more questions.")
                requestNextPageOfQuestions(++currentPage)
            }

            onPaginationInfoObtained(pagination)
            isLoadingMoreAnimals = false
        }
    }

    private fun onPaginationInfoObtained(pagination: Pagination) {
        currentPage = pagination.currentPage
        isLastPage = !pagination.canLoadMore
    }

    private fun onFailure(failure: Throwable) {

        when (failure) {
            is NetworkException,
            is NetworkUnavailableException -> {
                _state.value = state.value!!.copy(
                    loading = false,
                    failure = Event(failure)
                )
            }
            is NoMoreQuestionsException -> {
                _state.value = state.value!!.copy(
                    noMoreQuestions = true,
                    failure = Event(failure)
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}