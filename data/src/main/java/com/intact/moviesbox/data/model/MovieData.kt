package com.intact.moviesbox.data.model

data class MovieData(
    val id: String,
    val budget: Long,
    val imdbId: String,
    val overview: String,
    val isAdult: Boolean,
    val popularity: Float,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val original_title: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagLine: String,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int
)