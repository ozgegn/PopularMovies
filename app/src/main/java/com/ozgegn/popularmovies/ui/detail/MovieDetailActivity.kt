package com.ozgegn.popularmovies.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.ozgegn.popularmovies.R
import com.ozgegn.popularmovies.base.DataBindingActivity
import com.ozgegn.popularmovies.databinding.ActivityMovieDetailBinding
import com.ozgegn.popularmovies.ui.extensions.onTransformationEndContainerApplyParams
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MovieDetailActivity : DataBindingActivity() {

    private val binding: ActivityMovieDetailBinding by binding(R.layout.activity_movie_detail)
    val vm by viewModels<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationEndContainerApplyParams()
        super.onCreate(savedInstanceState)

        val movieId: Int = requireNotNull(intent.getIntExtra(EXTRA_MOVIE_ID, -1))

        subscribeUi()

        binding.apply {
            lifecycleOwner = this@MovieDetailActivity
            viewModel = vm.apply { getMovieDetail(movieId) }
        }
    }

    private fun subscribeUi() {
        vm.errorMessage.observe(this) {
            Timber.e("Ozge: $it")
        }
    }

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun startActivity(transformationLayout: TransformationLayout, id: Int) {
            val context = transformationLayout.context
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, id)
            TransformationCompat.startActivity(transformationLayout, intent)
        }
    }
}