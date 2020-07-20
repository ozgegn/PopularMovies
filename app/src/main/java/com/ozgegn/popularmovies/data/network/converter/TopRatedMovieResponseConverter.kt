package com.ozgegn.popularmovies.data.network.converter

import androidx.room.TypeConverter
import com.ozgegn.popularmovies.model.TopRatedMovieResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

open class TopRatedMovieResponseConverter {

    val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String): TopRatedMovieResponse? {
        val adapter: JsonAdapter<TopRatedMovieResponse> = moshi.adapter(TopRatedMovieResponse::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromJsonType(type: TopRatedMovieResponse): String{
        val adapter: JsonAdapter<TopRatedMovieResponse> = moshi.adapter(TopRatedMovieResponse::class.java)
        return adapter.toJson(type)
    }

}