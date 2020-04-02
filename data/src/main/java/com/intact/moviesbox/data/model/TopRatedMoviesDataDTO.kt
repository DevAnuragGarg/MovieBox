package com.intact.moviesbox.data.model

/**
 * data class having the top rated data
 */
data class TopRatedMoviesDataDTO(
    val pageNumber: String,
    val totalPages: String,
    val movieDTOS: ArrayList<MovieDataDTO>
)