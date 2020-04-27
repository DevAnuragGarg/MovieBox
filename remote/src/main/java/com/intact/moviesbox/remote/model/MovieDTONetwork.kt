package com.intact.moviesbox.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *  @Expose is used to allow or disallow serialization and deserialization.
 *  @Expose is optional and it has two configuration parameters: serialize
 *  and deserialize. By default they're set to true.
 *
 *  @SerializeName is used to set the key that json object will include ,
 *  however @Expose is used to decide whether the variable will be exposed
 *  for Serialisation and Deserialization ,or not
 */
data class MovieDTONetwork(
    @Expose @SerializedName("id") val id: Long,
    @Expose @SerializedName("title") val title: String,
    @Expose @SerializedName("budget") val budget: Long?,
    @Expose @SerializedName("adult") val adult: Boolean,
    @Expose @SerializedName("runtime") val runtime: Int?,
    @Expose @SerializedName("revenue") val revenue: Long?,
    @Expose @SerializedName("status") val status: String?,
    @Expose @SerializedName("imdb_id") val imdbId: String?,
    @Expose @SerializedName("tagline") val tagLine: String?,
    @Expose @SerializedName("overview") val overview: String,
    @Expose @SerializedName("vote_count") val voteCount: Int,
    @Expose @SerializedName("popularity") val popularity: Float,
    @Expose @SerializedName("poster_path") val posterPath: String?,
    @Expose @SerializedName("vote_average") val voteAverage: Float?,
    @Expose @SerializedName("release_date") val releaseDate: String,
    @Expose @SerializedName("backdrop_path") val backdropPath: String?,
    @Expose @SerializedName("original_title") val originalTitle: String,
    @Expose @SerializedName("original_language") val originalLanguage: String
)