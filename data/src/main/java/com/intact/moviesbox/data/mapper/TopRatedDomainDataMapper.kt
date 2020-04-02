package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.model.TopRatedMoviesDataDTO
import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.domain.entities.TopRatedMoviesDomainDTO
import javax.inject.Inject

class TopRatedDomainDataMapper @Inject constructor() :
    Mapper<TopRatedMoviesDomainDTO, TopRatedMoviesDataDTO> {

    override fun from(e: TopRatedMoviesDataDTO): TopRatedMoviesDomainDTO {
        val topRatedMovies = ArrayList<MovieDomainDTO>()

        // used the movie domain data mapper to convert
        // movie data into movie entity
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in e.movieDTOS) {
            topRatedMovies.add(movieDomainDataMapper.from(movie))
        }

        return TopRatedMoviesDomainDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = topRatedMovies
        )
    }

    override fun to(t: TopRatedMoviesDomainDTO): TopRatedMoviesDataDTO {
        val topRatedMovies = ArrayList<MovieDataDTO>()

        // used the movie domain data mapper to convert
        // movie entity into movie data
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in t.movies) {
            topRatedMovies.add(movieDomainDataMapper.to(movie))
        }

        return TopRatedMoviesDataDTO(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movieDTOS = topRatedMovies
        )
    }
}