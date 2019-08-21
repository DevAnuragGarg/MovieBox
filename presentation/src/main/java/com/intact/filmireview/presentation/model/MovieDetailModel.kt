package com.intact.filmireview.presentation.model

data class MovieDetailModel(
    val voteCount: String,
    val id: String,
    val video: Boolean,
    val voteAverage: String,
    val title: String,
    val popularity: String,
    val posterPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String
)