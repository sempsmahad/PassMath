package com.kh69.passmath.common.data.cache.daos

import androidx.room.*
import com.kh69.passmath.common.data.cache.model.cachedquestion.CachedQuestion
import io.reactivex.Flowable

@Dao
abstract class QuestionsDao {

    @Transaction
    @Query("SELECT * FROM questions")
    abstract fun getAllQuestions(): Flowable<List<CachedQuestion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertQuestion(
        question: CachedQuestion
    )

    suspend fun insertQuestions(questions: List<CachedQuestion>) {
        for (question in questions) {
            insertQuestion(
                question
            )
        }
    }
}
