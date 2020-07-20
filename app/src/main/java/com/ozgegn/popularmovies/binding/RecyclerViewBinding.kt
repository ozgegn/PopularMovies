package com.ozgegn.popularmovies.binding

import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozgegn.popularmovies.base.RecyclerViewPaginator
import com.ozgegn.popularmovies.model.TopRatedMovieResult
import com.ozgegn.popularmovies.ui.list.MovieListViewModel
import com.ozgegn.popularmovies.ui.list.TopRatedMovieListAdapter
import com.skydoves.whatif.whatIfNotNullOrEmpty

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("movieListPagination")
fun paginationMovieList(button: Button, viewModel: MovieListViewModel) {
    RecyclerViewPaginator(
        button = button,
        isLoading = { viewModel.isLoading().get() },
        loadMore = { viewModel.fetchMovies(it) },
        onLast = { false }
    )
}

@BindingAdapter("adapterMovieList")
fun bindMovieListAdapter(view: RecyclerView, list: List<TopRatedMovieResult>?) {
    list.whatIfNotNullOrEmpty {
        (view.adapter as? TopRatedMovieListAdapter)?.addMovieList(it)
    }
}

@BindingAdapter("adapterFilteredMovieList")
fun bindFilteredMovieList(view: RecyclerView, list: List<TopRatedMovieResult>?) {
    list.whatIfNotNullOrEmpty {
        (view.adapter as? TopRatedMovieListAdapter)?.addMovieListAfterReset(it)
    }
}