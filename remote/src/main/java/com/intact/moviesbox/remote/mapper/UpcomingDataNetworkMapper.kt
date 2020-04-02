package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.model.UpcomingMoviesDataDTO
import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.UpcomingMoviesDTONetwork
import javax.inject.Inject

class UpcomingDataNetworkMapper @Inject constructor() :
    Mapper<UpcomingMoviesDataDTO, UpcomingMoviesDTONetwork> {
    override fun from(e: UpcomingMoviesDTONetwork): UpcomingMoviesDataDTO {
        val upcomingMovies = ArrayList<MovieDataDTO>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in e.movies) {
            upcomingMovies.add(movieDataNetworkMapper.from(movie))
        }

        return UpcomingMoviesDataDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movieDTOS = upcomingMovies
        )
    }

    override fun to(t: UpcomingMoviesDataDTO): UpcomingMoviesDTONetwork {
        val upcomingMovies = ArrayList<MovieDTONetwork>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in t.movieDTOS) {
            upcomingMovies.add(movieDataNetworkMapper.to(movie))
        }

        return UpcomingMoviesDTONetwork(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = upcomingMovies
        )
    }
}