package com.kh69.passmath.data.cache

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*
import com.google.gson.reflect.TypeToken


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

