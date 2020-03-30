package com.intact.moviesbox.presentation.model

data class TrendingMoviesDTO(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieDTO>
)