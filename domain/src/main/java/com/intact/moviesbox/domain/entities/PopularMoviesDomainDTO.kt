package com.intact.moviesbox.domain.entities

/**
 * entity generated in the domain layer having the
 * popular movies response data. This is what we get from
 * the data layer using the mapper
 */
data class PopularMoviesDomainDTO(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieDomainDTO>
)
