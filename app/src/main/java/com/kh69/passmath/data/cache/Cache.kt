package com.kh69.passmath.data.cache

import io.reactivex.Flowable

interface Cache {
    fun getQuestions(): Flowable<List<CachedAnimalAggregate>>
    fun storeOrganizations(organizations: List<CachedOrganization>)
    fun storeNearbyAnimals(animals: List<CachedAnimalAggregate>)
    suspend fun getAllTypes(): List<String>

    fun searchAnimalsBy(
        nameOrBreed: String,
        age: String,
        type: String
    ): Flowable<List<CachedAnimalAggregate>>
}