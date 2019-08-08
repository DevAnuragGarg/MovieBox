package com.intact.filmireview.data.response

import com.google.gson.annotations.SerializedName
import com.intact.filmireview.data.model.MovieDTO

/**
 *  created by Anurag on 19-Mar-2019
 *
 *  @Expose is used to allow or disallow serialization and deserialization.
 *  @Expose is optional and it has two configuration parameters: serialize
 *  and deserialize. By default they're set to true.
 *
 *  @SerializeName is used to set the key that json object will include ,
 *  however @Expose is used to decide whether the variable will be exposed
 *  for Serialisation and Deserialisation ,or not
 */

data class MovieResponse(
    @SerializedName("page") val pageNumber: String,
    @SerializedName("total_pages") val totalPages: String,
    @SerializedName("results") val movies: ArrayList<MovieDTO>
)
