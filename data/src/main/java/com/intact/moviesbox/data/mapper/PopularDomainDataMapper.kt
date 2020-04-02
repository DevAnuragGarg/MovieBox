package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.model.PopularMoviesDataDTO
import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.domain.entities.PopularMoviesDomainDTO
import javax.inject.Inject

class PopularDomainDataMapper @Inject constructor() :
    Mapper<PopularMoviesDomainDTO, PopularMoviesDataDTO> {

    override fun from(e: PopularMoviesDataDTO): PopularMoviesDomainDTO {
        val popularMovies = ArrayList<MovieDomainDTO>()

        // used the movie domain data mapper to convert
        // movie data into movie entity
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in e.movieDTOS) {
            popularMovies.add(movieDomainDataMapper.from(movie))
        }

        return PopularMoviesDomainDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = popularMovies
        )
    }

    override fun to(t: PopularMoviesDomainDTO): PopularMoviesDataDTO {
        val popularMovies = ArrayList<MovieDataDTO>()

        // used the movie domain data mapper to convert
        // movie entity into movie data
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in t.movies) {
            popularMovies.add(movieDomainDataMapper.to(movie))
        }

        return PopularMoviesDataDTO(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movieDTOS = popularMovies
        )
    }
}