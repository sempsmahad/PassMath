package com.kh69.passmath.common.di

import com.kh69.passmath.common.data.PassMathQuestionRepository
import com.kh69.passmath.common.domain.repositories.QuestionRepository
import com.kh69.passmath.common.utils.CoroutineDispatchersProvider
import com.kh69.passmath.common.utils.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.disposables.CompositeDisposable

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

  @Binds
  @ActivityRetainedScoped
  abstract fun bindQuestionRepository(repository: PassMathQuestionRepository): QuestionRepository

  @Binds
  abstract fun bindDispatchersProvider(dispatchersProvider: CoroutineDispatchersProvider):
          DispatchersProvider

  companion object {
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()
  }
}