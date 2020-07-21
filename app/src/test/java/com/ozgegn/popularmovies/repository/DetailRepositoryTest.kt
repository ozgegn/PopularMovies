package com.ozgegn.popularmovies.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.ozgegn.popularmovies.BuildConfig
import com.ozgegn.popularmovies.data.local.MovieDetailsDao
import com.ozgegn.popularmovies.data.network.TmdbApi
import com.ozgegn.popularmovies.data.network.TmdbClient
import com.ozgegn.popularmovies.model.MovieDetailsResponse
import com.ozgegn.popularmovies.network.ApiUtil.getCall
import com.ozgegn.popularmovies.network.MockUtil.mockMoviesDetailResponse
import com.ozgegn.popularmovies.network.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailRepositoryTest {

    private lateinit var detailRepository: DetailRepository
    private lateinit var tmdbClient: TmdbClient
    private val service: TmdbApi = mock()
    private val detailDao: MovieDetailsDao = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = TestCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        tmdbClient = TmdbClient(service)
        detailRepository = DetailRepository(tmdbClient, detailDao)
    }

    @Test
    fun `should success when movie detail has proper data`() = runBlocking {

        val mockData = mockMoviesDetailResponse()
        whenever(detailDao.getMovieDetail(592350)).thenReturn(null)
        whenever(service.getMovieDetail(592350, BuildConfig.TMDB_KEY)).thenReturn(getCall(mockData))

        val loadData = detailRepository.fetchMovieDetail(592350) {}
        verify(detailDao, atLeastOnce()).getMovieDetail(592350)
        verify(service, atLeastOnce()).getMovieDetail(592350, BuildConfig.TMDB_KEY)

        val observer: Observer<MovieDetailsResponse> = mock()
        loadData.observeForever(observer)

        val updatedData = mockMoviesDetailResponse()
        whenever(detailDao.getMovieDetail(592350)).thenReturn(updatedData)

        loadData.postValue(updatedData)
        verify(observer).onChanged(updatedData)
        loadData.removeObserver(observer)
    }
}