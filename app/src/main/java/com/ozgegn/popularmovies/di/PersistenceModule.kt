package com.ozgegn.popularmovies.di

import android.app.Application
import androidx.room.Room
import com.ozgegn.popularmovies.data.local.AppDatabase
import com.ozgegn.popularmovies.data.local.MovieDetailsDao
import com.ozgegn.popularmovies.data.local.TopRatedMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application, AppDatabase::class.java, "movie.db"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideTopRatedMoviesDao(appDatabase: AppDatabase): TopRatedMovieDao {
        return appDatabase.topRatedMoviesDao()
    }

    @Provides
    @Singleton
    fun provideMovieDetailsDao(appDatabase: AppDatabase): MovieDetailsDao {
        return appDatabase.movieDetailDao()
    }
}