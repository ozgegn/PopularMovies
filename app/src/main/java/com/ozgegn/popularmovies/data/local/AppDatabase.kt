package com.ozgegn.popularmovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ozgegn.popularmovies.data.network.converter.*
import com.ozgegn.popularmovies.model.MovieDetailsResponse
import com.ozgegn.popularmovies.model.TopRatedMovieResult

@Database(
    entities = [MovieDetailsResponse::class, TopRatedMovieResult::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(
    value = [MovieCollectionInfoConverter::class, MovieGenreConverter::class, MovieProductionCompanyConverter::class,
        MovieProductionCountryConverter::class, MovieSpokenLanguageConverter::class, TopRatedMovieResultConverter::class,
        IntConverter::class, TopRatedMovieResponseConverter::class]
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun topRatedMoviesDao(): TopRatedMovieDao
    abstract fun movieDetailDao(): MovieDetailsDao

}