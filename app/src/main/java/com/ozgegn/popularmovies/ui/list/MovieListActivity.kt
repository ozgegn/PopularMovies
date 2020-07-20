package com.ozgegn.popularmovies.ui.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.ozgegn.popularmovies.R
import com.ozgegn.popularmovies.base.DataBindingActivity
import com.ozgegn.popularmovies.databinding.ActivityMovieListBinding
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListActivity : DataBindingActivity(), SearchView.OnQueryTextListener {

    private val binding: ActivityMovieListBinding by binding(R.layout.activity_movie_list)

    val vm by viewModels<MovieListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.mainToolbar)
        binding.apply {
            lifecycleOwner = this@MovieListActivity
            adapter = TopRatedMovieListAdapter()
            viewModel = vm.apply { fetchMovies(1) }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrBlank() && query.length > 2) {
            vm.filterMovies(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrBlank() && newText.length > 2) {
            vm.filterMovies(newText)
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_movie_list, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        val expandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                binding.buttonLoad.visibility = View.GONE
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                binding.adapter?.clearAdapter()
                vm.fetchMovies(1)
                binding.buttonLoad.visibility = View.VISIBLE
                return true
            }

        }
        menuItem.setOnActionExpandListener(expandListener)
        searchView.apply {
            queryHint = getString(R.string.hint_search)
            setOnQueryTextListener(this@MovieListActivity)
        }
        return super.onCreateOptionsMenu(menu)
    }

}