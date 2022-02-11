package com.kh69.passmath.data.cache

import androidx.lifecycle.LiveData
import retrofit2.http.Query
import java.util.*

@Dao
interface QuestionDAO {

    @Query("SELECT * FROM pattern")
    fun getPatterns(): LiveData<List<Pattern>>

    @Query("SELECT * FROM pattern WHERE id=(:id)")
    fun getPattern(id: UUID): LiveData<Pattern?>

    @Update
    fun updatePattern(pattern: Pattern)

    @Insert
    fun addPattern(pattern: Pattern)
}