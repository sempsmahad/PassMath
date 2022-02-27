package com.kh69.passmath

import android.app.Application
import androidx.room.Room
import com.kh69.passmath.data.QuestionDatabase

class App : Application() {
    private val DB_NAME = "quiz_database"

//    override fun onCreate() {
//        super.onCreate()
//        QuestionRepository.initialize(this)
//    }


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