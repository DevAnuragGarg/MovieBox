package com.intact.moviesbox.data.model

/**
 * data class having the upcoming data
 */
data class UpcomingMoviesData(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieData>
)