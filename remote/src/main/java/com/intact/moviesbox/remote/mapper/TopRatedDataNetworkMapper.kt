package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.TopRatedMoviesData
import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.TopRatedMoviesDTONetwork
import javax.inject.Inject

class TopRatedDataNetworkMapper @Inject constructor() :
    Mapper<TopRatedMoviesData, TopRatedMoviesDTONetwork> {
    override fun from(e: TopRatedMoviesDTONetwork): TopRatedMoviesData {
        val nowPlayingMovies = ArrayList<MovieData>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in e.movies) {
            nowPlayingMovies.add(movieDataNetworkMapper.from(movie))
        }

        return TopRatedMoviesData(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = nowPlayingMovies
        )
    }

    override fun to(t: TopRatedMoviesData): TopRatedMoviesDTONetwork {
        val nowPlayingMovies = ArrayList<MovieDTONetwork>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in t.movies) {
            nowPlayingMovies.add(movieDataNetworkMapper.to(movie))
        }

        return TopRatedMoviesDTONetwork(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = nowPlayingMovies
        )
    }

}