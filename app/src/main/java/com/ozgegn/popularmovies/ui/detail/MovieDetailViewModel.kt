package com.ozgegn.popularmovies.ui.detail

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.ozgegn.popularmovies.base.LiveCoroutinesViewModel
import com.ozgegn.popularmovies.model.MovieDetailsResponse
import com.ozgegn.popularmovies.repository.DetailRepository
import timber.log.Timber

class MovieDetailViewModel @ViewModelInject constructor(
    private val movieDetailRepository: DetailRepository
) : LiveCoroutinesViewModel() {

    val movieLiveData: LiveData<MovieDetailsResponse>
    private var movieIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.d("init MovieDetailViewModel")

        movieLiveData = movieIdLiveData.switchMap { id ->
            launchOnViewModelScope {
                movieDetailRepository.fetchMovieDetail(id) {
                    errorMessage.postValue(it)
                }
            }
        }

    }

    fun getMovieDetail(id: Int) {
        movieIdLiveData.postValue(id)
    }

    fun isLoading(): ObservableBoolean = movieDetailRepository.isLoading

}