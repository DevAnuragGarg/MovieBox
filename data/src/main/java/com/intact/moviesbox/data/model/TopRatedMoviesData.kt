package com.intact.moviesbox.data.model

/**
 * data class having the top rated data
 */
data class TopRatedMoviesData(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieData>
)