package com.ozgegn.popularmovies.data.network

import com.ozgegn.popularmovies.BuildConfig
import com.ozgegn.popularmovies.model.MovieDetailsResponse
import com.ozgegn.popularmovies.model.TopRatedMovieResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.request
import javax.inject.Inject

class TmdbClient @Inject constructor(
    private val tmdbApi: TmdbApi
) {

    fun fetchTopRatedMovies(
        page: Int,
        onResult: (response: ApiResponse<TopRatedMovieResponse>) -> Unit
    ) {
        tmdbApi.getTopRatedMovies(BuildConfig.TMDB_KEY, page).request(onResult)
    }

    fun fetchMovieDetail(
        movieId: Int,
        onResult: (response: ApiResponse<MovieDetailsResponse>) -> Unit
    ) {
        tmdbApi.getMovieDetail(movieId, BuildConfig.TMDB_KEY).request(onResult)
    }

}