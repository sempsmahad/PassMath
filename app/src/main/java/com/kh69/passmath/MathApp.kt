package com.kh69.passmath

import android.app.Application
import androidx.room.Room
import com.kh69.passmath.data.QuestionDatabase

class MathApp : Application() {
    private val DB_NAME = "quiz_database"

    companion object {
        lateinit var database: QuestionDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            QuestionDatabase::class.java,
            DB_NAME
        )
            .build()
    }
}