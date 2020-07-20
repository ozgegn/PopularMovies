package com.ozgegn.popularmovies.base

import android.view.View
import android.widget.Button

class RecyclerViewPaginator(
    private val button: Button,
    private val isLoading: () -> Boolean,
    private val loadMore: (Int) -> Unit,
    private val onLast: () -> Boolean = { true }
) : View.OnClickListener {

    var currentPage: Int = 1

    init {
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (onLast() || isLoading()) return

        loadMore(++currentPage)
    }

}