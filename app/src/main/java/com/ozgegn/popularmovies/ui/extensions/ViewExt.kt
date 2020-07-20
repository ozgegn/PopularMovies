package com.ozgegn.popularmovies.ui.extensions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.transformationlayout.onTransformationEndContainer

fun AppCompatActivity.onTransformationEndContainerApplyParams() {
    onTransformationEndContainer(intent.getParcelableExtra("com.skydoves.transformationlayout"))
}

fun View.gone(shouldBeGone: Boolean) {
    visibility = if (shouldBeGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
