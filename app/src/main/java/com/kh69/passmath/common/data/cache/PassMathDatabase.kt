package com.kh69.passmath.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kh69.passmath.common.data.cache.daos.QuestionsDao
import com.kh69.passmath.common.data.cache.model.cachedquestion.CachedQuestion

@Database(
    entities = [
        CachedQuestion::class
    ],
    version = 1
)
abstract class PassMathDatabase : RoomDatabase() {
    abstract fun questionsDao(): QuestionsDao
}