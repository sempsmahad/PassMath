package com.kh69.passmath

import android.app.Application
import com.kh69.logging.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PassMathApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() {
        Logger.init()
    }


}