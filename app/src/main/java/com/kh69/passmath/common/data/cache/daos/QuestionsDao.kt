package com.kh69.passmath.common.data.cache.daos

import androidx.room.*
import com.kh69.passmath.common.data.cache.model.cachedquestion.CachedQuestion
import com.raywenderlich.android.petsave.common.data.cache.model.cachedanimal.*
import io.reactivex.Flowable

@Dao
abstract class QuestionsDao {

  @Transaction
  @Query("SELECT * FROM animals ORDER BY animalId DESC")
  abstract fun getAllAnimals(): Flowable<List<CachedAnimalAggregate>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insertAnimalAggregate(
      animal: CachedQuestion,
      photos: List<CachedPhoto>,
      videos: List<CachedVideo>,
      tags: List<CachedTag>
  )

  suspend fun insertAnimalsWithDetails(animalAggregates: List<CachedAnimalAggregate>) {
    for (animalAggregate in animalAggregates) {
      insertAnimalAggregate(
          animalAggregate.animal,
          animalAggregate.photos,
          animalAggregate.videos,
          animalAggregate.tags
      )
    }
  }

  @Query("SELECT DISTINCT type FROM animals")
  abstract suspend fun getAllTypes(): List<String>

  @Transaction
  @Query("""
    SELECT * FROM animals
      WHERE name LIKE '%' || :name || '%' AND
      AGE LIKE '%' || :age || '%'
      AND type LIKE '%' || :type || '%'
  """)
  abstract fun searchAnimalsBy(
      name: String,
      age: String,
      type: String
  ): Flowable<List<CachedAnimalAggregate>>
}
