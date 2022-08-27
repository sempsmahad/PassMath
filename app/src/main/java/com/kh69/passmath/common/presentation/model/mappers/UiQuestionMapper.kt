package com.kh69.passmath.common.presentation.model.mappers

import com.kh69.passmath.common.domain.model.question.Question
import com.kh69.passmath.common.presentation.model.UIQuestion
import com.raywenderlich.android.petsave.common.domain.model.animal.Animal
import com.raywenderlich.android.petsave.common.presentation.model.UIAnimal
import com.raywenderlich.android.petsave.common.presentation.model.mappers.UiMapper
import javax.inject.Inject

class UiQuestionMapper @Inject constructor(): UiMapper<Question, UIQuestion> {

  override fun mapToView(input: Question): UIQuestion {
    return UIQuestion(
        id = input.id,
        name = input.name,
        photo = input.media.getFirstSmallestAvailablePhoto()
    )
  }
}
