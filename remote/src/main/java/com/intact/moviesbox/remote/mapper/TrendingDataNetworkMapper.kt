package com.intact.moviesbox.remote.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.data.model.TrendingMoviesDataDTO
import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.TrendingMoviesDTONetwork
import javax.inject.Inject

class TrendingDataNetworkMapper @Inject constructor() :
    Mapper<TrendingMoviesDataDTO, TrendingMoviesDTONetwork> {
    override fun from(e: TrendingMoviesDTONetwork): TrendingMoviesDataDTO {
        val trendingMovies = ArrayList<MovieDataDTO>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in e.movies) {
            trendingMovies.add(movieDataNetworkMapper.from(movie))
        }

        return TrendingMoviesDataDTO(
            pageNumber = e.pageNumber,
            totalPages = e.totalPages,
            movieDTOS = trendingMovies
        )
    }

    override fun to(t: TrendingMoviesDataDTO): TrendingMoviesDTONetwork {
        val trendingMovies = ArrayList<MovieDTONetwork>()

        // used the movie data network mapper to convert
        // movie network into movie data
        val movieDataNetworkMapper = MovieDataNetworkMapper()

        for (movie in t.movieDTOS) {
            trendingMovies.add(movieDataNetworkMapper.to(movie))
        }

        return TrendingMoviesDTONetwork(
            pageNumber = t.pageNumber,
            totalPages = t.totalPages,
            movies = trendingMovies
        )
    }
}