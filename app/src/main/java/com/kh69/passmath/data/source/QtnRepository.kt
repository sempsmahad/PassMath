package com.kh69.passmath.data.source

import androidx.lifecycle.LiveData
import com.kh69.passmath.AppExecutors
import com.kh69.passmath.data.Question
import com.kh69.passmath.data.Resource
import com.kh69.passmath.data.source.local.MathDatabase
import com.kh69.passmath.data.source.local.QuestionsDao
import com.kh69.passmath.data.source.remote.APIUtils
import com.kh69.passmath.data.source.remote.MathService

/**
 * Repository that handles Repo instances.
 *
 * unfortunate naming :/ .
 * Repo - value object name
 * Repository - type of this class.
 */
class QtnRepository constructor(
    private val appExecutors: AppExecutors,
    private val db: MathDatabase,
    private val dao: QuestionsDao,
    private val service: MathService = APIUtils.getMathService()
) {

    // old method
//    fun getQuestions(): LiveData<Resource<List<Question>>> {
//        return object : NetworkBoundResource<List<Question>, List<Question>>(appExecutors) {
//            override fun saveCallResult(item: List<Question>) {
//                dao.insertQuestions(item)
//            }
//
//            override fun shouldFetch(data: List<Question>?): Boolean {
//                return data == null || data.isEmpty()
//            }
//
//            override fun loadFromDb() = dao.observeQuestions()
//
//            override fun createCall() = service.getQuestions()
//
//        }.asLiveData()
//    }

    fun getQuestions(): List<Question> {
        //check if db has questions
        //if it does return those questions
        //else fetch questions from network (extra -- check if network is available else return msg)
        //save in db
        //repeat call
    }

    fun getQuestion(id: String): LiveData<Resource<Question>> {
        return object : NetworkBoundResource<Question, Question>(appExecutors) {
            override fun saveCallResult(item: Question) {
                dao.insert(item)
            }

            override fun shouldFetch(data: Question?) = data == null

            override fun loadFromDb() = dao.load(id)

            override fun createCall() = service.getQuestion(id)

        }.asLiveData()
    }

//    fun updateQuestion(question: Question): LiveData<Resource<Int>> {
//        return object : NetworkBoundResource<Int, Question>(appExecutors) {
//            override fun saveCallResult(item: Question) {
//                dao.updateQuestion(item)
//            }
//
//            override fun shouldFetch(data: Int?) = data == 1
//
//            override fun loadFromDb() = dao.updateQuestion(question)
//
//            override fun createCall() = service.updateQuestion(question.id, question)
//
//        }.asLiveData()
//    }

//    fun deleteQuestion(id: String): LiveData<Resource<Int>> {
//        return object : NetworkBoundResource<Int, Question>(appExecutors) {
//            override fun saveCallResult(item: Question) {
//                dao.deleteQuestionById(item.id)
//            }
//
//            override fun shouldFetch(data: Int?) = data == 1
//
//            override fun loadFromDb() = dao.deleteQuestionById(id)
//
//            override fun createCall() = service.deleteQuestion(id)
//
//        }.asLiveData()
//    }

//    fun deleteQuestions(): LiveData<Resource<Int>> {
//        return object : NetworkBoundResource<Int, List<Question>>(appExecutors) {
//            override fun saveCallResult(item: List<Question>) {
//                dao.deleteQuestions()
//            }
//
//            override fun shouldFetch(data: Int?) = data == 1
//
//            override fun loadFromDb() {
//                return when (dao.deleteQuestions() == 1) {
//                    1    -> LiveData(1)
//                    else -> LiveData < Int(0) >
//                }
//            }
//
//            override fun createCall() = service.deleteQuestions()
//
//
//        }.asLiveData()
//    }

}
