package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieDTONetwork
import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.NowPlayingMoviesDTONetwork
import com.intact.moviesbox.data.model.NowPlayingMoviesData
import javax.inject.Inject

class NowPlayingDataNetworkMapper @Inject constructor(): Mapper<NowPlayingMoviesData, NowPlayingMoviesDTONetwork> {
    override fun from(e: NowPlayingMoviesDTONetwork): NowPlayingMoviesData {
        val nowPlayingMovies = ArrayList<MovieData>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in e.movies) {
            nowPlayingMovies.add(movieDataNetworkMapper.from(movie))
        }

        return NowPlayingMoviesData(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = nowPlayingMovies
        )
    }

    override fun to(t: NowPlayingMoviesData): NowPlayingMoviesDTONetwork {
        val nowPlayingMovies = ArrayList<MovieDTONetwork>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in t.movies) {
            nowPlayingMovies.add(movieDataNetworkMapper.to(movie))
        }

        return NowPlayingMoviesDTONetwork(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = nowPlayingMovies
        )
    }

}