package com.kh69.passmath.common.data.di

import com.kh69.passmath.common.data.preferences.PassMathPreferences
import com.kh69.passmath.common.data.preferences.Preferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

  @Binds
  abstract fun providePreferences(preferences: PassMathPreferences): Preferences
}