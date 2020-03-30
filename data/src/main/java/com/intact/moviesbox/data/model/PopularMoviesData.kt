package com.intact.moviesbox.data.model

/**
 * data class having the popular data
 */
data class PopularMoviesData(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieData>
)