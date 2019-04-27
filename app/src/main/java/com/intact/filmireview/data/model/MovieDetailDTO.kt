package com.intact.filmireview.data.model


/**
 * Created by Anurag Garg on 2019-04-24.
 */
data class MovieDetailDTO(
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Long,
    //geners
    val id: String,
    val imdb_id: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String,
//                           val production_companies : String,
//                           val production_countries ,
    val release_date: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val vote_average: Float,
    val vote_count: Int
)