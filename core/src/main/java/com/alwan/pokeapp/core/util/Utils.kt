package com.alwan.pokeapp.core.util

import android.animation.ObjectAnimator
import android.util.Log
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.ProgressBar
import com.alwan.pokeapp.core.R
import com.bumptech.glide.Glide

fun String.getUriLastPath(): String? {
    return try {
        val segments = this.split("/").toTypedArray()
        val id = segments[segments.size - 2]
        id
    } catch (e: Exception) {
        Log.e("Utils getUriLastPath", e.message.toString())
        null
    }
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.pokemon_placeholder)
        .into(this)
}

fun ProgressBar.setBigMax(max: Int) {
    this.max = max * 1000
}

fun ProgressBar.animateTo(progressTo: Int, startDelay: Long) {
    val animation = ObjectAnimator.ofInt(
        this,
        "progress",
        this.progress,
        progressTo * 1000
    )
    animation.duration = 500
    animation.interpolator = DecelerateInterpolator()
    animation.startDelay = startDelay
    animation.start()
}