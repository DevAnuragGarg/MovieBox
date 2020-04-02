package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.model.TopRatedMoviesDataDTO
import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.TopRatedMoviesDTONetwork
import javax.inject.Inject

class TopRatedDataNetworkMapper @Inject constructor() :
    Mapper<TopRatedMoviesDataDTO, TopRatedMoviesDTONetwork> {
    override fun from(e: TopRatedMoviesDTONetwork): TopRatedMoviesDataDTO {
        val nowPlayingMovies = ArrayList<MovieDataDTO>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in e.movies) {
            nowPlayingMovies.add(movieDataNetworkMapper.from(movie))
        }

        return TopRatedMoviesDataDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movieDTOS = nowPlayingMovies
        )
    }

    override fun to(t: TopRatedMoviesDataDTO): TopRatedMoviesDTONetwork {
        val nowPlayingMovies = ArrayList<MovieDTONetwork>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in t.movieDTOS) {
            nowPlayingMovies.add(movieDataNetworkMapper.to(movie))
        }

        return TopRatedMoviesDTONetwork(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = nowPlayingMovies
        )
    }

}