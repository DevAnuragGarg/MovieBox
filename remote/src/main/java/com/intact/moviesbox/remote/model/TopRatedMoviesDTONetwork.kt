package com.intact.moviesbox.remote.model

import com.google.gson.annotations.SerializedName

data class TopRatedMoviesDTONetwork(
    @SerializedName("page") val pageNumber: String,
    @SerializedName("total_pages") val totalPages: String,
    @SerializedName("results") val movies: ArrayList<MovieDTONetwork>
)