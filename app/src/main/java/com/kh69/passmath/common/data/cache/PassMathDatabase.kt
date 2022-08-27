package com.kh69.passmath.common.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kh69.passmath.common.data.cache.daos.QuestionsDao
import com.kh69.passmath.common.data.cache.model.cachedquestion.CachedQuestion
import com.raywenderlich.android.petsave.common.data.cache.daos.OrganizationsDao
import com.raywenderlich.android.petsave.common.data.cache.model.cachedanimal.*
import com.raywenderlich.android.petsave.common.data.cache.model.cachedorganization.CachedOrganization

@Database(
    entities = [
        CachedPhoto::class,
        CachedVideo::class,
        CachedTag::class,
        CachedQuestion::class,
        CachedOrganization::class,
        CachedAnimalTagCrossRef::class
    ],
    version = 1
)
abstract class PassMathDatabase : RoomDatabase() {
    abstract fun organizationsDao(): OrganizationsDao
    abstract fun animalsDao(): QuestionsDao
}