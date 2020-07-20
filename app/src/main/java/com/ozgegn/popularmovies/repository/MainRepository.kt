package com.ozgegn.popularmovies.repository

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.ozgegn.popularmovies.base.BaseRepository
import com.ozgegn.popularmovies.data.local.TopRatedMovieDao
import com.ozgegn.popularmovies.data.network.TmdbClient
import com.ozgegn.popularmovies.model.TopRatedMovieResult
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val tmdbClient: TmdbClient,
    private val topRatedMovieDao: TopRatedMovieDao
) : BaseRepository {

    override var isLoading: ObservableBoolean = ObservableBoolean(false)

    suspend fun fetchTopRatedMovie(page: Int, error: (String) -> Unit) =
        withContext(Dispatchers.IO) {

            Timber.tag("MainRepository").d("fetchTopRatedMovie")
            val movieLiveData = MutableLiveData<List<TopRatedMovieResult>>()
            var movieList = topRatedMovieDao.getMovieList(page)
            if (movieList.isNullOrEmpty()) {
                isLoading.set(true)
                tmdbClient.fetchTopRatedMovies(page) {
                    isLoading.set(false)
                    it.onSuccess {
                        data.whatIfNotNull { response ->
                            movieList = response.results
                            movieList.forEach { movie -> movie.page = page }
                            movieLiveData.postValue(movieList)
                            topRatedMovieDao.insertMovieList(movieList)
                        }
                    }
                        .onError { error(message()) }
                        .onException { error(message()) }
                }
            }
            movieLiveData.apply { postValue(movieList) }
        }

    suspend fun fetchFilteredMovies(query: String, error: (String) -> Unit) =
        withContext(Dispatchers.IO) {
            Timber.tag("MainRepository").d("fetchFilteredMovies")
            val filteredMoviesLiveData = MutableLiveData<List<TopRatedMovieResult>>()
            val filteredMovieList = topRatedMovieDao.searchMovie(query)
            filteredMoviesLiveData.apply { postValue(filteredMovieList) }
        }
}