package com.intact.moviesbox.ui.listeners

import android.widget.ImageView
import com.intact.moviesbox.presentation.model.MovieDTO

interface OnMovieItemClickListener {

    fun onMovieItemClicked(movie: MovieDTO, imageView: ImageView)
}