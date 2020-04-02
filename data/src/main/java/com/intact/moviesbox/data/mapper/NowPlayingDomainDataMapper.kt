package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.model.NowPlayingMoviesDataDTO
import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.domain.entities.NowPlayingMoviesDomainDTO
import javax.inject.Inject

class NowPlayingDomainDataMapper @Inject constructor() :
    Mapper<NowPlayingMoviesDomainDTO, NowPlayingMoviesDataDTO> {

    override fun from(e: NowPlayingMoviesDataDTO): NowPlayingMoviesDomainDTO {
        val nowPlayingMovies = ArrayList<MovieDomainDTO>()

        // used the movie domain data mapper to convert
        // movie data into movie entity
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in e.movieDTOS) {
            nowPlayingMovies.add(movieDomainDataMapper.from(movie))
        }

        return NowPlayingMoviesDomainDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = nowPlayingMovies
        )
    }

    override fun to(t: NowPlayingMoviesDomainDTO): NowPlayingMoviesDataDTO {
        val nowPlayingMovies = ArrayList<MovieDataDTO>()

        // used the movie domain data mapper to convert
        // movie entity into movie data
        val movieDomainDataMapper = MovieDomainDataMapper()

        for (movie in t.movies) {
            nowPlayingMovies.add(movieDomainDataMapper.to(movie))
        }

        return NowPlayingMoviesDataDTO(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movieDTOS = nowPlayingMovies)
    }
}