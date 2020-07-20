package com.ozgegn.popularmovies.data.network.converter

import androidx.room.TypeConverter
import com.ozgegn.popularmovies.model.MovieProductionCountry
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

open class MovieProductionCountryConverter {
    val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String): List<MovieProductionCountry>? {
        val listType = Types.newParameterizedType(List::class.java, MovieProductionCountry::class.java)
        val adapter: JsonAdapter<List<MovieProductionCountry>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromJsonType(type: List<MovieProductionCountry>): String{
        val listType = Types.newParameterizedType(List::class.java, MovieProductionCountry::class.java)
        val adapter: JsonAdapter<List<MovieProductionCountry>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}