/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kh69.passmath

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.kh69.passmath.data.source.QtnRepositoryImpl
import com.kh69.passmath.data.source.local.MathDatabase

/**
 * A Service Locator for the [QtnRepositoryImpl]
 *
 */
object ServiceLocator {
    private var database: MathDatabase? = null

    @Volatile
    var questionsRepository: QtnRepositoryImpl? = null
        @VisibleForTesting set

    fun provideQuestionsRepository(context: Context): QtnRepositoryImpl {
        synchronized(this) {
            return questionsRepository ?: questionsRepository ?: createQuestionsRepository(context)
        }
    }

    private fun createQuestionsRepository(context: Context): QtnRepositoryImpl {
        val newRepo = QtnRepositoryImpl(
            AppExecutors(),
            db = database ?: createDataBase(context),
            dao = (database ?: createDataBase(context)).questionDao(),
//            service = APIUtils.getMathService()
        )
        questionsRepository = newRepo
        return newRepo
    }

    @VisibleForTesting
    fun createDataBase(
        context: Context,
        inMemory: Boolean = false
    ): MathDatabase {
        val result = if (inMemory) {
            // Use a faster in-memory database for tests
            Room.inMemoryDatabaseBuilder(context.applicationContext, MathDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        } else {
            // Real database using SQLite
            Room.databaseBuilder(
                context.applicationContext,
                MathDatabase::class.java, "Questions.db"
            ).build()
        }
        database = result
        return result
    }

}
