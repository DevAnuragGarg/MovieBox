package com.intact.moviesbox.domain.entities

/**
 * entity generated in the domain layer having the
 * upcoming movies response data. This is what we get from
 * the data layer using the mapper
 */
data class UpcomingMoviesDomainDTO(
    val pageNumber: String,
    val totalPages: String,
    val movies: ArrayList<MovieDomainDTO>
)
