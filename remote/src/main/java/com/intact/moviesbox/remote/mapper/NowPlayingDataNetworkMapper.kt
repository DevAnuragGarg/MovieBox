package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.model.NowPlayingMoviesDataDTO
import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.NowPlayingMoviesDTONetwork
import javax.inject.Inject

class NowPlayingDataNetworkMapper @Inject constructor() :
    Mapper<NowPlayingMoviesDataDTO, NowPlayingMoviesDTONetwork> {
    override fun from(e: NowPlayingMoviesDTONetwork): NowPlayingMoviesDataDTO {
        val nowPlayingMovies = ArrayList<MovieDataDTO>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in e.movies) {
            nowPlayingMovies.add(movieDataNetworkMapper.from(movie))
        }

        return NowPlayingMoviesDataDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movieDTOS = nowPlayingMovies
        )
    }

    override fun to(t: NowPlayingMoviesDataDTO): NowPlayingMoviesDTONetwork {
        val nowPlayingMovies = ArrayList<MovieDTONetwork>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in t.movieDTOS) {
            nowPlayingMovies.add(movieDataNetworkMapper.to(movie))
        }

        return NowPlayingMoviesDTONetwork(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = nowPlayingMovies
        )
    }

}