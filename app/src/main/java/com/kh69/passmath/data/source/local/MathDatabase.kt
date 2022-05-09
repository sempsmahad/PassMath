package com.kh69.passmath.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kh69.passmath.data.Question
import com.kh69.passmath.data.cache.DbTypeConverters

/**
 * The Room Database that contains the Question table.
 *
 */
@Database(entities = [Question::class], version = 1)
@TypeConverters(DbTypeConverters::class)
abstract class MathDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionsDao
}
