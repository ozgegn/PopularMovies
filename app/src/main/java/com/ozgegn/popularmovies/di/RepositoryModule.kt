package com.ozgegn.popularmovies.di

import com.ozgegn.popularmovies.data.local.MovieDetailsDao
import com.ozgegn.popularmovies.data.local.TopRatedMovieDao
import com.ozgegn.popularmovies.data.network.TmdbClient
import com.ozgegn.popularmovies.repository.DetailRepository
import com.ozgegn.popularmovies.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMainRepository(
        tmdbClient: TmdbClient,
        topRatedMovieDao: TopRatedMovieDao
    ): MainRepository {
        return MainRepository(tmdbClient, topRatedMovieDao)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideDetailRepository(
        tmdbClient: TmdbClient,
        detailsDao: MovieDetailsDao
    ): DetailRepository {
        return DetailRepository(tmdbClient, detailsDao)
    }
}