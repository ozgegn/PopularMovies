package com.ozgegn.popularmovies.data.network

import com.ozgegn.popularmovies.model.MovieDetailsResponse
import com.ozgegn.popularmovies.model.TopRatedMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/top_rated?language=en-US")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<TopRatedMovieResponse>

    @GET("movie/{movie_id}?language=en-US")
    fun getMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetailsResponse>

}