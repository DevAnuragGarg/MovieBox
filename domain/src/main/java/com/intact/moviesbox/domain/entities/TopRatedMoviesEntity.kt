package com.intact.moviesbox.domain.entities

/**
 * entity generated in the domain layer having the
 * now movies response data. This is what we get from
 * the data layer using the mapper
 */
data class TopRatedMoviesEntity(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieEntity>
)
