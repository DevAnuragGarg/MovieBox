package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.PopularMoviesData
import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.PopularMoviesDTONetwork
import javax.inject.Inject

class PopularDataNetworkMapper @Inject constructor() :
    Mapper<PopularMoviesData, PopularMoviesDTONetwork> {
    override fun from(e: PopularMoviesDTONetwork): PopularMoviesData {
        val popularMovies = ArrayList<MovieData>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in e.movies) {
            popularMovies.add(movieDataNetworkMapper.from(movie))
        }

        return PopularMoviesData(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = popularMovies
        )
    }

    override fun to(t: PopularMoviesData): PopularMoviesDTONetwork {
        val popularMovies = ArrayList<MovieDTONetwork>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in t.movies) {
            popularMovies.add(movieDataNetworkMapper.to(movie))
        }

        return PopularMoviesDTONetwork(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = popularMovies
        )
    }
}