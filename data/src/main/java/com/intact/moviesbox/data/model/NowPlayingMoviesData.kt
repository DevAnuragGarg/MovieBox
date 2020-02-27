package com.intact.moviesbox.data.model

/**
 * data class having the now playing data
 */
data class NowPlayingMoviesData(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieData>
)