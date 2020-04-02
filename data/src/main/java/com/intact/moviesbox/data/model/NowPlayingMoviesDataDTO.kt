package com.intact.moviesbox.data.model

/**
 * data class having the now playing data
 */
data class NowPlayingMoviesDataDTO(
    val pageNumber: String,
    val totalPages: String,
    val movieDTOS: ArrayList<MovieDataDTO>
)