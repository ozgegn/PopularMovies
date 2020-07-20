package com.ozgegn.popularmovies.data.network.converter

import androidx.room.TypeConverter
import com.ozgegn.popularmovies.model.MovieCollectionInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

open class MovieCollectionInfoConverter {
    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String): MovieCollectionInfo? {
        val adapter : JsonAdapter<MovieCollectionInfo> = moshi.adapter(MovieCollectionInfo::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromJsonType(type: MovieCollectionInfo?): String {
        val adapter: JsonAdapter<MovieCollectionInfo> = moshi.adapter(MovieCollectionInfo::class.java)
        return adapter.toJson(type)
    }

}