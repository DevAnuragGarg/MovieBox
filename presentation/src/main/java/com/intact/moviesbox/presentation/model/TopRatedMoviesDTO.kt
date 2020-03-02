package com.intact.moviesbox.presentation.model

data class TopRatedMoviesDTO(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieDTO>
)