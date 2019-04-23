package com.intact.filmireview.ui.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Anurag Garg on 26/03/19.
 */

data class MovieDTO(
    @Expose @SerializedName("vote_count") val voteCount: String,
    @Expose @SerializedName("id") val id: String,
    @Expose @SerializedName("video") val video: String,
    @Expose @SerializedName("vote_average") val voteAverage: String,
    @Expose @SerializedName("title") val title: String,
    @Expose @SerializedName("popularity") val popularity: String,
    @Expose @SerializedName("poster_path") val posterPath: String,
    @Expose @SerializedName("original_language") val originalLanguage: String,
    @Expose @SerializedName("original_title") val originalTitle: String,
    @Expose @SerializedName("backdrop_path") val backdropPath: String,
    @Expose @SerializedName("adult") val adult: String,
    @Expose @SerializedName("overview") val overview: String,
    @Expose @SerializedName("release_date") val releaseDate: String
)