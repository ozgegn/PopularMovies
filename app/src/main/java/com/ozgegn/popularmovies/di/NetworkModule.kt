package com.ozgegn.popularmovies.di

import com.ozgegn.popularmovies.data.network.HttpRequestInterceptor
import com.ozgegn.popularmovies.data.network.TmdbApi
import com.ozgegn.popularmovies.data.network.TmdbClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpRequestInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideTmdbService(retrofit: Retrofit): TmdbApi {
        return retrofit.create(TmdbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTmdbClient(tmdbApi: TmdbApi): TmdbClient {
        return TmdbClient(tmdbApi)
    }

}