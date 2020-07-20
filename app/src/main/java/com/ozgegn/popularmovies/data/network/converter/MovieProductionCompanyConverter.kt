package com.ozgegn.popularmovies.data.network.converter

import androidx.room.TypeConverter
import com.ozgegn.popularmovies.model.MovieProductionCompany
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

open class MovieProductionCompanyConverter {

    val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String): List<MovieProductionCompany>? {
        val listType = Types.newParameterizedType(List::class.java, MovieProductionCompany::class.java)
        val adapter: JsonAdapter<List<MovieProductionCompany>> = moshi.adapter(listType)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromJsonType(type: List<MovieProductionCompany>): String{
        val listType = Types.newParameterizedType(List::class.java, MovieProductionCompany::class.java)
        val adapter: JsonAdapter<List<MovieProductionCompany>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }

}