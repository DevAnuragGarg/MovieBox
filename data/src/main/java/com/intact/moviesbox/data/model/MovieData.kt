package com.intact.moviesbox.data.model

/**
 *  file to have the movie related data in data layer
 */
data class MovieData(
    val id: Long,
    val title: String,
    val budget: Long?,
    val adult: Boolean,
    val runtime: Int?,
    val revenue: Long?,
    val status: String?,
    val imdbId: String?,
    val tagLine: String?,
    val overview: String,
    val voteCount: Int,
    val popularity: Float,
    val posterPath: String,
    val voteAverage: Float,
    val releaseDate: String,
    val backdropPath: String,
    val originalTitle: String,
    val originalLanguage: String
)