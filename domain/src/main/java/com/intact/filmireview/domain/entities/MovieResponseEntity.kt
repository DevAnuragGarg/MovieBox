package com.intact.filmireview.domain.entities

import com.google.gson.annotations.SerializedName

data class MovieResponseEntity(
    @SerializedName("page") val pageNumber: String,
    @SerializedName("total_pages") val totalPages: String,
    @SerializedName("results") val movies: ArrayList<MovieDetailEntity>
)
