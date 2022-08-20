package com.kh69.passmath.legacy.data.cache

import androidx.room.TypeConverter
import java.util.*


class DbTypeConverters {
    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

}

