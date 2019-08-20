package com.intact.filmireview.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *  created by Anurag on 08-08-2019
 *
 *  @Expose is used to allow or disallow serialization and deserialization.
 *  @Expose is optional and it has two configuration parameters: serialize
 *  and deserialize. By default they're set to true.
 *
 *  @SerializeName is used to set the key that json object will include ,
 *  however @Expose is used to decide whether the variable will be exposed
 *  for Serialisation and Deserialisation ,or not
 */

data class MovieDetailEntity(
    @Expose @SerializedName("vote_count") val voteCount: String,
    @Expose @SerializedName("id") val id: String,
    @Expose @SerializedName("video") val video: Boolean,
    @Expose @SerializedName("vote_average") val voteAverage: String,
    @Expose @SerializedName("title") val title: String,
    @Expose @SerializedName("popularity") val popularity: String,
    @Expose @SerializedName("poster_path") val posterPath: String,
    @Expose @SerializedName("original_language") val originalLanguage: String,
    @Expose @SerializedName("original_title") val originalTitle: String,
    @Expose @SerializedName("backdrop_path") val backdropPath: String,
    @Expose @SerializedName("adult") val adult: Boolean,
    @Expose @SerializedName("overview") val overview: String,
    @Expose @SerializedName("release_date") val releaseDate: String
)