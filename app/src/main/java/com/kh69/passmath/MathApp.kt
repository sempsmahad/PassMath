package com.kh69.passmath

import android.app.Application
import com.kh69.passmath.data.source.QtnRepository
import com.kh69.passmath.data.source.QuestionsRepository

class MathApp : Application() {
//    private val DB_NAME = "quiz_database"

    // Depends on the flavor,
    val questionRepository: QtnRepository
        get() = ServiceLocator.provideQuestionsRepository(this)

//    companion object {
//        lateinit var database: QuestionDatabase
//            private set
//    }

    override fun onCreate() {
        super.onCreate()
//        database = Room.databaseBuilder(
//            this,
//            QuestionDatabase::class.java,
//            DB_NAME
//        )
//            .build()
    }
}