package com.ozgegn.popularmovies.data.network.converter

import androidx.room.TypeConverter
import com.ozgegn.popularmovies.model.MovieGenre
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

open class MovieGenreConverter {

    val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String): List<MovieGenre>? {
        val listType = Types.newParameterizedType(List::class.java, MovieGenre::class.java)
        val adapter: JsonAdapter<List<MovieGenre>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromJsonType(type: List<MovieGenre>?): String {
        val listType = Types.newParameterizedType(List::class.java, MovieGenre::class.java)
        val adapter: JsonAdapter<List<MovieGenre>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}