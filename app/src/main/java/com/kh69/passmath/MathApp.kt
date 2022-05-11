package com.kh69.passmath

import android.app.Application
import com.kh69.passmath.data.source.QtnRepository

class MathApp : Application() {
//    private val DB_NAME = "quiz_database"

    companion object {
        private var instance: MathApp? = null

        fun getContext(): MathApp {
            return instance!!
        }
    }

    // Depends on the flavor,
    val questionRepository: QtnRepository
        get() = ServiceLocator.provideQuestionsRepository(this)
    
    override fun onCreate() {
        super.onCreate()
        instance = this

//        database = Room.databaseBuilder(
//            this,
//            QuestionDatabase::class.java,
//            DB_NAME
//        )
//            .build()
    }
}