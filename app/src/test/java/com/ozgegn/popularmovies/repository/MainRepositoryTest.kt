package com.ozgegn.popularmovies.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.ozgegn.popularmovies.BuildConfig
import com.ozgegn.popularmovies.data.local.TopRatedMovieDao
import com.ozgegn.popularmovies.data.network.TmdbApi
import com.ozgegn.popularmovies.data.network.TmdbClient
import com.ozgegn.popularmovies.model.TopRatedMovieResponse
import com.ozgegn.popularmovies.model.TopRatedMovieResult
import com.ozgegn.popularmovies.network.ApiUtil.getCall
import com.ozgegn.popularmovies.network.MockUtil.mockTopRatedMovieList
import com.ozgegn.popularmovies.network.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainRepositoryTest {

    private lateinit var mainRepository: MainRepository
    private lateinit var tmdbClient: TmdbClient
    private val service: TmdbApi = mock()
    private var mainDao: TopRatedMovieDao = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = TestCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        tmdbClient = TmdbClient(service)
        mainRepository = MainRepository(tmdbClient, mainDao)
    }

    @Test
    fun `should success when page value is bigger than zero`() = runBlocking {
        val mockData = TopRatedMovieResponse(
            page = 1,
            total_results = 1,
            total_pages = 1,
            results = mockTopRatedMovieList()
        )

        whenever(mainDao.getMovieList(page_ = 0)).thenReturn(emptyList())
        whenever(service.getTopRatedMovies(BuildConfig.TMDB_KEY, 1)).thenReturn(getCall(mockData))

        val loadData = mainRepository.fetchTopRatedMovie(page = 1) {}
        verify(mainDao, atLeastOnce()).getMovieList(page_ = 1)
        verify(service, atLeastOnce()).getTopRatedMovies(BuildConfig.TMDB_KEY, 1)

        val observer: Observer<List<TopRatedMovieResult>> = mock()
        loadData.observeForever(observer)

        val updatedData = mockTopRatedMovieList()
        whenever(mainDao.getMovieList(1)).thenReturn(updatedData)

        loadData.postValue(updatedData)
        verify(observer).onChanged(updatedData)
        loadData.removeObserver(observer)
    }
}