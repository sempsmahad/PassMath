package com.kh69.passmath.common.data.di

import android.content.Context
import androidx.room.Room
import com.kh69.passmath.common.data.cache.Cache
import com.kh69.passmath.common.data.cache.PassMathDatabase
import com.kh69.passmath.common.data.cache.RoomCache
import com.kh69.passmath.common.data.cache.daos.QuestionsDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): PassMathDatabase {
            return Room.databaseBuilder(
                context,
                PassMathDatabase::class.java,
                "passmath.db"
            )
                .build()
        }

        @Provides
        fun provideAnimalsDao(
            passMathDatabase: PassMathDatabase
        ): QuestionsDao = passMathDatabase.questionsDao()


    }
}