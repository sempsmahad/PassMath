package com.kh69.passmath.common.data.cache

import com.kh69.passmath.common.data.cache.daos.QuestionsDao
import com.kh69.passmath.common.data.cache.model.cachedquestion.CachedQuestion
import io.reactivex.Flowable
import javax.inject.Inject

class RoomCache @Inject constructor(
    private val questionsDao: QuestionsDao
) : Cache {

    override fun getQuestions(): Flowable<List<CachedQuestion>> {
        return questionsDao.getAllQuestions()
    }

    override suspend fun storeQuestions(animals: List<CachedQuestion>) {
        questionsDao.insertQuestions(animals)
    }
}