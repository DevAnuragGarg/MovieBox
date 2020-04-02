package com.intact.moviesbox.data.model

/**
 * data class having the upcoming data
 */
data class UpcomingMoviesDataDTO(
    val pageNumber: String,
    val totalPages: String,
    val movieDTOS: ArrayList<MovieDataDTO>
)