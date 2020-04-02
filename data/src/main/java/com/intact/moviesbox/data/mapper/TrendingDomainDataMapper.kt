package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.model.TrendingMoviesDataDTO
import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.domain.entities.TrendingMoviesDomainDTO
import javax.inject.Inject

class TrendingDomainDataMapper @Inject constructor() :
    Mapper<TrendingMoviesDomainDTO, TrendingMoviesDataDTO> {

    override fun from(e: TrendingMoviesDataDTO): TrendingMoviesDomainDTO {
        val trendingMovies = ArrayList<MovieDomainDTO>()

        // used the movie domain data mapper to convert
        // movie data into movie entity
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in e.movieDTOS) {
            trendingMovies.add(movieDomainDataMapper.from(movie))
        }

        return TrendingMoviesDomainDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = trendingMovies
        )
    }

    override fun to(t: TrendingMoviesDomainDTO): TrendingMoviesDataDTO {
        val trendingMovies = ArrayList<MovieDataDTO>()

        // used the movie domain data mapper to convert
        // movie entity into movie data
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in t.movies) {
            trendingMovies.add(movieDomainDataMapper.to(movie))
        }

        return TrendingMoviesDataDTO(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movieDTOS = trendingMovies
        )
    }
}