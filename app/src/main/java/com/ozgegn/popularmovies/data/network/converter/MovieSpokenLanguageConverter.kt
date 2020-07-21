package com.ozgegn.popularmovies.data.network.converter

import androidx.room.TypeConverter
import com.ozgegn.popularmovies.model.MovieSpokenLanguage
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

open class MovieSpokenLanguageConverter {
    val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String): List<MovieSpokenLanguage>? {
        val listType = Types.newParameterizedType(List::class.java, MovieSpokenLanguage::class.java)
        val adapter: JsonAdapter<List<MovieSpokenLanguage>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromJsonType(type: List<MovieSpokenLanguage>): String {
        val listType = Types.newParameterizedType(List::class.java, MovieSpokenLanguage::class.java)
        val adapter: JsonAdapter<List<MovieSpokenLanguage>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}