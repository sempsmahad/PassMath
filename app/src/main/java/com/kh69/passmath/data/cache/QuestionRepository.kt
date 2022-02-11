package com.kh69.passmath.data.cache

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import org.w3c.dom.Node
import java.util.*
import java.util.concurrent.Executors


private const val DATABASE_NAME = "pattern-database"

class QuestionRepository private constructor(context: Context) {

    private val database : PatternDatabase = Room.databaseBuilder(
        context.applicationContext,
        PatternDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val patternDao = database.patternDao()
    private val nodeDao = database.nodeDao()

    private val executor = Executors.newSingleThreadExecutor()

    fun getPatterns(): LiveData<List<Pattern>> = patternDao.getPatterns()
    fun getPattern(id: UUID): LiveData<Pattern?> = patternDao.getPattern(id)

    fun getNodes(patternId: UUID): LiveData<List<Node>?> = nodeDao.getNodes(patternId)
    fun getNode(id: UUID): LiveData<Node?> = nodeDao.getNode(id)

    fun updatePattern(pattern: Pattern) {
        executor.execute {
            patternDao.updatePattern(pattern)
        }
    }
    fun updateNode(node: Node) {
        executor.execute {
            nodeDao.updateNode(node)
        }
    }

    fun addPattern(pattern: Pattern) {
        executor.execute {
            patternDao.addPattern(pattern)
        }
    }
    fun addNode(node: Node) {
        executor.execute {
            nodeDao.addNode(node)
        }
    }

    companion object {
        private var INSTANCE: PatternRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = PatternRepository(context)
            }
        }

        fun get(): PatternRepository {
            return INSTANCE ?:
            throw IllegalStateException("PatternRepository must be initialized")
        }
    }
}