package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.model.UpcomingMoviesDataDTO
import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.domain.entities.UpcomingMoviesDomainDTO
import javax.inject.Inject

class UpcomingDomainDataMapper @Inject constructor() :
    Mapper<UpcomingMoviesDomainDTO, UpcomingMoviesDataDTO> {

    override fun from(e: UpcomingMoviesDataDTO): UpcomingMoviesDomainDTO {
        val upcomingMovies = ArrayList<MovieDomainDTO>()

        // used the movie domain data mapper to convert
        // movie data into movie entity
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in e.movieDTOS) {
            upcomingMovies.add(movieDomainDataMapper.from(movie))
        }

        return UpcomingMoviesDomainDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = upcomingMovies
        )
    }

    override fun to(t: UpcomingMoviesDomainDTO): UpcomingMoviesDataDTO {
        val upcomingMovies = ArrayList<MovieDataDTO>()

        // used the movie domain data mapper to convert
        // movie entity into movie data
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in t.movies) {
            upcomingMovies.add(movieDomainDataMapper.to(movie))
        }

        return UpcomingMoviesDataDTO(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movieDTOS = upcomingMovies
        )
    }
}