package com.kh69.passmath.questions.presentation

sealed class QuizEvent {
    object RequestInitialQuestionsList : QuizEvent()
    object RequestMoreQuestions : QuizEvent()
    object GoToNextQuestion : QuizEvent()
    object GoToPreviousQuestion : QuizEvent()
}