package com.kh69.passmath.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kh69.passmath.data.cache.Question
import org.w3c.dom.Node

private const val DATABASE_NAME = "question-database"

@Database(entities = [ Question::class ], version=2)
@TypeConverters(DbTypeConverters::class)
abstract class PatternDatabase : RoomDatabase() {
    abstract fun patternDao(): PatternDao
    abstract fun nodeDao(): NodeDao
}