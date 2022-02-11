package com.kh69.passmath.data.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*


@Dao
interface QuestionDAO {
    @Query("SELECT * FROM question")
    fun getQuestions(): LiveData<List<Question>>

    @Query("SELECT * FROM question WHERE id=(:id)")
    fun getQuestion(id: UUID): LiveData<Question?>

    @Update
    fun updateQuestion(question: Question)

    @Insert
    fun addQuestion(question: Question)
}