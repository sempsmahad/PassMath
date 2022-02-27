package com.kh69.passmath

import android.app.Application
import com.kh69.passmath.data.cache.QuestionRepository

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        QuestionRepository.initialize(this)
    }
}