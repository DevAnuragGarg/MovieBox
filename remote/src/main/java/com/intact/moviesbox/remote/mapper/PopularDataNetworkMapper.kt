package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.model.PopularMoviesDataDTO
import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.PopularMoviesDTONetwork
import javax.inject.Inject

class PopularDataNetworkMapper @Inject constructor() :
    Mapper<PopularMoviesDataDTO, PopularMoviesDTONetwork> {
    override fun from(e: PopularMoviesDTONetwork): PopularMoviesDataDTO {
        val popularMovies = ArrayList<MovieDataDTO>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in e.movies) {
            popularMovies.add(movieDataNetworkMapper.from(movie))
        }

        return PopularMoviesDataDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movieDTOS = popularMovies
        )
    }

    override fun to(t: PopularMoviesDataDTO): PopularMoviesDTONetwork {
        val popularMovies = ArrayList<MovieDTONetwork>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in t.movieDTOS) {
            popularMovies.add(movieDataNetworkMapper.to(movie))
        }

        return PopularMoviesDTONetwork(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = popularMovies
        )
    }
}