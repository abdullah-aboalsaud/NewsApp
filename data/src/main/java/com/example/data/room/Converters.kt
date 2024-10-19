package com.example.data.room

import androidx.room.TypeConverter
import com.example.data.api.models.headlines.SourceDM
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    @TypeConverter
    fun fromSourceDM(source: SourceDM?): String? {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun toSourceDM(sourceString: String?): SourceDM? {
        return Gson().fromJson(sourceString, object : TypeToken<SourceDM>() {}.type)
    }
}