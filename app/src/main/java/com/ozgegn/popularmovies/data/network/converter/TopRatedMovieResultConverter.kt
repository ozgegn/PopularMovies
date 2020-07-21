package com.ozgegn.popularmovies.data.network.converter

import androidx.room.TypeConverter
import com.ozgegn.popularmovies.model.TopRatedMovieResult
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

open class TopRatedMovieResultConverter {
    val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String): List<TopRatedMovieResult>? {
        val listType = Types.newParameterizedType(List::class.java, TopRatedMovieResult::class.java)
        val adapter: JsonAdapter<List<TopRatedMovieResult>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromJsonType(type: List<TopRatedMovieResult>): String {
        val listType = Types.newParameterizedType(List::class.java, TopRatedMovieResult::class.java)
        val adapter: JsonAdapter<List<TopRatedMovieResult>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}