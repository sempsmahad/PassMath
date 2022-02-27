package com.kh69.passmath.data.cache

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*


@Dao
interface QuestionDAO {
    @Query("SELECT * FROM question")
    fun getQuestions(): LiveData<List<Question>>

    @Query("SELECT * FROM question WHERE id=(:id)")
    fun getQuestion(id: UUID): LiveData<Question?>

    @Update
    fun updateQuestion(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuestion(question: Question)

    @Query("DELETE FROM question")
    fun clearQuestions()
}