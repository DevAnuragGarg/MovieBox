package com.intact.moviesbox.data.model

/**
 * data class having the popular data
 */
data class PopularMoviesDataDTO(
    val pageNumber: String,
    val totalPages: String,
    val movieDTOS: ArrayList<MovieDataDTO>
)