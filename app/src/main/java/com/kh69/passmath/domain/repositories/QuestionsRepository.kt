package com.kh69.passmath.domain.repositories

import android.app.appsearch.SearchResults
import io.reactivex.Flowable

interface AnimalRepository {

    fun getAnimals(): Flowable<List<Animal>>
    suspend fun requestMoreAnimals(pageToLoad: Int, numberOfItems: Int): PaginatedAnimals
    suspend fun storeAnimals(animals: List<AnimalWithDetails>)
    suspend fun getAnimalTypes(): List<String>
    fun getAnimalAges(): List<AnimalWithDetails.Details.Age>
    fun searchCachedAnimalsBy(searchParameters: SearchParameters): Flowable<SearchResults>

    suspend fun searchAnimalsRemotely(
        pageToLoad: Int,
        searchParameters: SearchParameters,
        numberOfItems: Int
    ): PaginatedAnimals
}