package com.ozgegn.popularmovies.repository

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.ozgegn.popularmovies.base.BaseRepository
import com.ozgegn.popularmovies.data.local.MovieDetailsDao
import com.ozgegn.popularmovies.data.network.TmdbClient
import com.ozgegn.popularmovies.model.MovieDetailsResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val tmdbClient: TmdbClient,
    private val detailsDao: MovieDetailsDao
) : BaseRepository {

    override var isLoading: ObservableBoolean = ObservableBoolean(false)

    suspend fun fetchMovieDetail(id: Int, error: (String) -> Unit) = withContext(Dispatchers.IO) {

        val detailLiveData = MutableLiveData<MovieDetailsResponse>()
        var detailData = detailsDao.getMovieDetail(id)
        if (detailData == null) {
            isLoading.set(true)
            tmdbClient.fetchMovieDetail(id) {
                isLoading.set(false)
                it.onSuccess {
                    data.whatIfNotNull { response ->
                        detailData = response
                        detailsDao.insertMovieDetail(response)
                    }
                }
                    .onError {
                        error(message())
                    }
                    .onException {
                        error(message())
                    }
            }
            delay(2000)
        }

        detailLiveData.apply { postValue(detailData) }

    }
}