package com.kh69.passmath.common.data.cache

import com.kh69.passmath.common.data.cache.model.cachedquestion.CachedQuestion
import io.reactivex.Flowable

interface Cache {
    fun getQuestions(): Flowable<List<CachedQuestion>>

    suspend fun storeQuestions(animals: List<CachedQuestion>)
}