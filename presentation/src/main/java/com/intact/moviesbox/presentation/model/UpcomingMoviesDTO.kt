package com.intact.moviesbox.presentation.model

data class UpcomingMoviesDTO(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieDTO>
)