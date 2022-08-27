package com.kh69.passmath.common.data.cache

import com.kh69.passmath.common.data.cache.daos.QuestionsDao
import com.kh69.passmath.common.data.cache.model.cachedquestion.CachedQuestion
import io.reactivex.Flowable
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val questionsDao: QuestionsDao
) : Cache {

  override fun getQuestions(): Flowable<List<CachedQuestion>> {
    return questionsDao.getAllAnimals()
  }

  override suspend fun storeNearbyAnimals(animals: List<CachedAnimalAggregate>) {
    questionsDao.insertAnimalsWithDetails(animals)
  }

  override suspend fun getAllTypes(): List<String> {
    return questionsDao.getAllTypes()
  }

  override fun searchAnimalsBy(
      name: String,
      age: String,
      type: String
  ): Flowable<List<CachedAnimalAggregate>> {
    return questionsDao.searchAnimalsBy(name, age, type)
  }
}