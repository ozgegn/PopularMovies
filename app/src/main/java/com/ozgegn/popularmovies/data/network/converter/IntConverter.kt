package com.ozgegn.popularmovies.data.network.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

open class IntConverter {
    val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String): List<Int>? {
        val listType = Types.newParameterizedType(List::class.java, Integer::class.java)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromJsonType(type: List<Int>?): String {
        val listType = Types.newParameterizedType(List::class.java, Integer::class.java)
        val adapter: JsonAdapter<List<Int>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}