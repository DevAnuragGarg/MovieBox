package com.intact.moviesbox.presentation.model

data class NowPlayingMoviesDTO(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieDTO>
)