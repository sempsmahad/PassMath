package com.kh69.passmath.common.data.cache.model.cachedquestion

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class CachedTag(
    @PrimaryKey
    val tag: String
)