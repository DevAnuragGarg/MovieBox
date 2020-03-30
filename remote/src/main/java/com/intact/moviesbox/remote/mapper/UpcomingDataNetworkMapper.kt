package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.UpcomingMoviesData
import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.UpcomingMoviesDTONetwork
import javax.inject.Inject

class UpcomingDataNetworkMapper @Inject constructor() :
    Mapper<UpcomingMoviesData, UpcomingMoviesDTONetwork> {
    override fun from(e: UpcomingMoviesDTONetwork): UpcomingMoviesData {
        val upcomingMovies = ArrayList<MovieData>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in e.movies) {
            upcomingMovies.add(movieDataNetworkMapper.from(movie))
        }

        return UpcomingMoviesData(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movies = upcomingMovies
        )
    }

    override fun to(t: UpcomingMoviesData): UpcomingMoviesDTONetwork {
        val upcomingMovies = ArrayList<MovieDTONetwork>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in t.movies) {
            upcomingMovies.add(movieDataNetworkMapper.to(movie))
        }

        return UpcomingMoviesDTONetwork(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = upcomingMovies
        )
    }
}