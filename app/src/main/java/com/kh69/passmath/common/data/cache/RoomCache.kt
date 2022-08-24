package com.kh69.passmath.common.data.cache

import com.kh69.passmath.common.data.cache.daos.QuestionsDao
import com.raywenderlich.android.petsave.common.data.cache.daos.OrganizationsDao
import com.raywenderlich.android.petsave.common.data.cache.model.cachedanimal.CachedAnimalAggregate
import com.raywenderlich.android.petsave.common.data.cache.model.cachedorganization.CachedOrganization
import io.reactivex.Flowable
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val questionsDao: QuestionsDao,
    private val organizationsDao: OrganizationsDao
) : Cache {

  override suspend fun storeOrganizations(organizations: List<CachedOrganization>) {
    organizationsDao.insert(organizations)
  }

  override fun getNearbyAnimals(): Flowable<List<CachedAnimalAggregate>> {
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