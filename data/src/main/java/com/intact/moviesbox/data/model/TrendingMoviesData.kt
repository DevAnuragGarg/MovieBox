package com.intact.moviesbox.data.model

/**
 * data class having the trending data
 */
data class TrendingMoviesData(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieData>
)