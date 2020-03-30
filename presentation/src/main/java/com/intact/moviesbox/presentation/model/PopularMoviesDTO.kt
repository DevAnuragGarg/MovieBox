package com.intact.moviesbox.presentation.model

data class PopularMoviesDTO(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieDTO>
)