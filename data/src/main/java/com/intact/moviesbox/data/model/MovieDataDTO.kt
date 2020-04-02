package com.intact.moviesbox.data.model

/**
 *  file to have the movie related data in data layer
 */
data class MovieDataDTO(
    val id: Long,
    val budget: Long?,
    val runtime: Int?,
    val title: String,
    val adult: Boolean,
    val revenue: Long?,
    val voteCount: Int,
    val status: String?,
    val imdbId: String?,
    val tagLine: String?,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val voteAverage: Float?,
    val releaseDate: String,
    val backdropPath: String?,
    val originalTitle: String,
    val originalLanguage: String
)