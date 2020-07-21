package com.ozgegn.popularmovies.network

import com.ozgegn.popularmovies.model.TopRatedMovieResult
import com.ozgegn.popularmovies.model.TopRatedMovieResponse
import com.ozgegn.popularmovies.model.MovieDetailsResponse
import com.ozgegn.popularmovies.model.MovieCollectionInfo

object MockUtil {

    fun mockTopRatedMovieResult() = TopRatedMovieResult(
        popularity = 43.668,
        vote_count = 218,
        video = false,
        poster_path = "/zGVbrulkupqpbwgiNedkJPyQum4.jpg",
        id = 592350,
        adult = false,
        backdrop_path = "/9guoVF7zayiiUq5ulKQpt375VIy.jpg",
        original_language = "ja",
        original_title = "僕のヒーローアカデミア THE MOVIE ヒーローズ：ライジング",
        genre_ids = listOf(),
        title = "My Hero Academia: Heroes Rising",
        vote_average = 8.9,
        overview = "Class 1-A visits Nabu Island where they finally get to do some real hero work. The place is so peaceful that it's more like a vacation … until they're attacked by a villain with an unfathomable Quirk! His power is eerily familiar, and it looks like Shigaraki had a hand in the plan. But with All Might retired and citizens' lives on the line, there's no time for questions. Deku and his friends are the next generation of heroes, and they're the island's only hope.",
        release_date = "2019-12-20"
    )

    fun mockTopRatedMovieResponse() = TopRatedMovieResponse(
        page = 1, total_results = 1, total_pages = 1, results = listOf(mockTopRatedMovieResult())
    )

    fun mockTopRatedMovieList() = listOf(mockTopRatedMovieResult())

    fun mockMoviesDetailResponse() = MovieDetailsResponse(
        adult = false,
        backdrop_path = "/9guoVF7zayiiUq5ulKQpt375VIy.jpg",
        belongs_to_collection = MovieCollectionInfo(
            id = 662002,
            name = "My Hero Academia Collection",
            poster_path = "/o1HPwiDPWRlm9LnHWqqCLrnGpoY.jpg",
            backdrop_path = "/45ugfrqKgSIrTSdJIw6kwABztq8.jpg"
        ),
        budget = 0,
        genres = listOf(),
        homepage = "https://heroaca-movie.com",
        id = 592350,
        imdb_id = "tt11107074",
        original_language = "ja",
        original_title = "僕のヒーローアカデミア THE MOVIE ヒーローズ：ライジング",
        overview = "Class 1-A visits Nabu Island where they finally get to do some real hero work. The place is so peaceful that it's more like a vacation … until they're attacked by a villain with an unfathomable Quirk! His power is eerily familiar, and it looks like Shigaraki had a hand in the plan. But with All Might retired and citizens' lives on the line, there's no time for questions. Deku and his friends are the next generation of heroes, and they're the island's only hope.",
        popularity = 34.085,
        poster_path = "/zGVbrulkupqpbwgiNedkJPyQum4.jpg",
        production_companies = listOf(),
        production_countries = listOf(),
        release_date = "2019-12-20",
        revenue = 29900850,
        runtime = 104,
        spoken_languages = listOf(),
        status = "Released",
        tagline = "",
        title = "My Hero Academia: Heroes Rising",
        video = false,
        vote_average = 8.9,
        vote_count = 225
    )
}