package com.intact.moviesbox.presentation.model

data class MovieDTO(
    val id: Long,
    val runtime: Int?,
    val budget: Long?,
    val title: String,
    val voteCount: Int,
    val revenue: Long?,
    val adult: Boolean,
    val status: String?,
    val imdbId: String?,
    val tagLine: String?,
    val overview: String,
    val popularity: Float,
    val posterPath: String?,
    val voteAverage: Float?,
    val releaseDate: String,
    val backdropPath: String?,
    val originalTitle: String,
    val originalLanguage: String
)