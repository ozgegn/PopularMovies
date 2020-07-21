package com.ozgegn.popularmovies.ui.list

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.ozgegn.popularmovies.base.LiveCoroutinesViewModel
import com.ozgegn.popularmovies.model.TopRatedMovieResult
import com.ozgegn.popularmovies.repository.MainRepository
import timber.log.Timber

class MovieListViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

    private var movieFetchLiveData: MutableLiveData<Int> = MutableLiveData()
    private var movieFilterLiveData: MutableLiveData<String> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    val moviesData: LiveData<List<TopRatedMovieResult>>

    val filteredMovieLiveData: LiveData<List<TopRatedMovieResult>>

    init {
        Timber.d("init MovieListViewModel")
        moviesData = movieFetchLiveData.switchMap { page ->
            launchOnViewModelScope {
                mainRepository.fetchTopRatedMovie(page) {
                    errorMessage.postValue(it)
                }
            }
        }

        filteredMovieLiveData = movieFilterLiveData.switchMap { query ->
            launchOnViewModelScope {
                mainRepository.fetchFilteredMovies(query) {
                    errorMessage.postValue(it)
                }
            }
        }
    }

    fun fetchMovies(page: Int) {
        movieFetchLiveData.postValue(page)
    }

    fun filterMovies(query: String) {
        movieFilterLiveData.postValue(query)
    }

    fun isLoading(): ObservableBoolean = mainRepository.isLoading
}