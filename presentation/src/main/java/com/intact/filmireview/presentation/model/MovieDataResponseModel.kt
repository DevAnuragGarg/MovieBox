package com.intact.filmireview.presentation.model

data class MovieDataResponseModel(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieDetailModel>
)