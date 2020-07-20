package com.ozgegn.popularmovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopRatedMovieResponse(
    @field:Json(name = "page") val page: Int = 0,
    @field:Json(name = "total_results") val total_results: Int?,
    @field:Json(name = "total_pages") val total_pages: Int?,
    @field:Json(name = "results") val results: List<TopRatedMovieResult>
)

@Entity(tableName = "topRatedMovies")
@JsonClass(generateAdapter = true)
data class TopRatedMovieResult(
    var page: Int = 0,
    @field:Json(name = "poster_path") val poster_path: String?,
    @field:Json(name = "adult") val adult: Boolean?,
    @field:Json(name = "overview") val overview: String?,
    @field:Json(name = "release_date") val release_date: String?,
    @field:Json(name = "genre_ids") val genre_ids: List<Int>?,
    @field:Json(name = "id") @PrimaryKey val id: Int,
    @field:Json(name = "original_title") val original_title: String?,
    @field:Json(name = "original_language") val original_language: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "backdrop_path") val backdrop_path: String?,
    @field:Json(name = "popularity") val popularity: Double?,
    @field:Json(name = "vote_count") val vote_count: Int?,
    @field:Json(name = "video") val video: Boolean?,
    @field:Json(name = "vote_average") val vote_average: Double?
) {

    fun getImageUrl(): String {
        return "https://image.tmdb.org/t/p/w500/${poster_path}"
    }

}