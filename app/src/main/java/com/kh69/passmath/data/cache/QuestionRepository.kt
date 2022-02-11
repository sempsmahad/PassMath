package com.kh69.passmath.data.cache

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.kh69.passmath.data.QuestionDatabase
import org.w3c.dom.Node
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "pattern-database"

class QuestionRepository private constructor(context: Context) {

    private val database : QuestionDatabase = Room.databaseBuilder(
        context.applicationContext,
        QuestionDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val questionDAO = database.questionDAO()

    private val executor = Executors.newSingleThreadExecutor()

    fun getQuestions(): LiveData<List<Question>> = questionDAO.getQuestions()
    fun getQuestion(id: UUID): LiveData<Question?> = questionDAO.getQuestion(id)


    fun updateQuestion(question: Question) {
        executor.execute {
            questionDAO.updateQuestion(question)
        }
    }

    fun addQuestion(question: Question) {
        executor.execute {
            questionDAO.addQuestion(question)
        }
    }

    companion object {
        private var INSTANCE: QuestionRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = QuestionRepository(context)
            }
        }

        fun get(): QuestionRepository {
            return INSTANCE ?:
            throw IllegalStateException("PatternRepository must be initialized")
        }
    }
}