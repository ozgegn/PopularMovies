package com.ozgegn.popularmovies.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ozgegn.popularmovies.R
import com.ozgegn.popularmovies.databinding.TopRatedMovieItemBinding
import com.ozgegn.popularmovies.model.TopRatedMovieResult
import com.ozgegn.popularmovies.ui.detail.MovieDetailActivity

class TopRatedMovieListAdapter :
    RecyclerView.Adapter<TopRatedMovieListAdapter.TopRatedMovieListViewHolder>() {

    private var onClickTime = System.currentTimeMillis()
    private var items: MutableList<TopRatedMovieResult> = mutableListOf()

    class TopRatedMovieListViewHolder(val binding: TopRatedMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMovieListViewHolder {
        val binding = DataBindingUtil.inflate<TopRatedMovieItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.top_rated_movie_item,
            parent,
            false
        )
        return TopRatedMovieListViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: TopRatedMovieListViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            movie = item
            executePendingBindings()
            root.setOnClickListener {
                val currentTime = System.currentTimeMillis()
                if (currentTime - onClickTime > movieItemTransformationLayout.duration) {
                    onClickTime = currentTime
                    MovieDetailActivity.startActivity(movieItemTransformationLayout, item.id)
                }
            }
        }
    }

    fun addMovieList(movieList: List<TopRatedMovieResult>) {
        items.addAll(movieList)
        notifyDataSetChanged()
    }

    fun addMovieListAfterReset(movieList: List<TopRatedMovieResult>) {
        items.apply {
            clear()
            addAll(movieList)
        }
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        items.clear()
    }

    override fun getItemCount(): Int = items.size
}