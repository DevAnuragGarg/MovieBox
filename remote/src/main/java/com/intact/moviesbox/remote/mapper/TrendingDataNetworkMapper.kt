package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.TrendingMoviesData
import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.TrendingMoviesDTONetwork
import javax.inject.Inject

class TrendingDataNetworkMapper @Inject constructor() :
    Mapper<TrendingMoviesData, TrendingMoviesDTONetwork> {
    override fun from(e: TrendingMoviesDTONetwork): TrendingMoviesData {
        val trendingMovies = ArrayList<MovieData>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in e.movies) {
            trendingMovies.add(movieDataNetworkMapper.from(movie))
        }

        return TrendingMoviesData(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = trendingMovies
        )
    }

    override fun to(t: TrendingMoviesData): TrendingMoviesDTONetwork {
        val trendingMovies = ArrayList<MovieDTONetwork>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in t.movies) {
            trendingMovies.add(movieDataNetworkMapper.to(movie))
        }

        return TrendingMoviesDTONetwork(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = trendingMovies
        )
    }
}