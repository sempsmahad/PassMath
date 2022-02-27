package com.kh69.passmath.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kh69.passmath.Answer
import com.kh69.passmath.data.cache.DbTypeConverters
import com.kh69.passmath.data.cache.Question
import com.kh69.passmath.data.cache.QuestionDAO


@Database(entities = [Question::class], version = 1)
@TypeConverters(DbTypeConverters::class)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDAO(): QuestionDAO
}
