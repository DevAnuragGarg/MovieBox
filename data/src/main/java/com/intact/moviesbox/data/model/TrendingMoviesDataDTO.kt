package com.intact.moviesbox.data.model

/**
 * data class having the trending data
 */
data class TrendingMoviesDataDTO(
    val pageNumber: String,
    val totalPages: String,
    val movieDTOS: ArrayList<MovieDataDTO>
)