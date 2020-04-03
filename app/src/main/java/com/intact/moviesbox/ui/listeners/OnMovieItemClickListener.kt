package com.intact.moviesbox.ui.listeners

import com.intact.moviesbox.presentation.model.MovieDTO

interface OnMovieItemClickListener {

    fun onMovieItemClicked(movie: MovieDTO)
}