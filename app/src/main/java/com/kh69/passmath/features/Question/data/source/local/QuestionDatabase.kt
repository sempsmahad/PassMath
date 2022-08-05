package com.kh69.passmath.features.question.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kh69.passmath.features.question.domain.model.Question

@Database(
    entities = [Question::class],
    version = 1
)
abstract class QuestionDatabase: RoomDatabase() {

    abstract val questionDao: QuestionDao

    companion object {
        const val DATABASE_NAME = "questions_db"
    }
}