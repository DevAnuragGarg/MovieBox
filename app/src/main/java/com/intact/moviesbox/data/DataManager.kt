package com.intact.moviesbox.data

import com.intact.moviesbox.data.request.MovieRequests
import com.intact.moviesbox.util.MOVIE_DB_API_KEY
import javax.inject.Inject

/**
 *  implementation of base data manager
 */

class DataManager @Inject constructor(private val movieRequests: MovieRequests) : BaseDataManager {

    override fun getPopularMovies(pageNumber: String) =
        movieRequests.getPopularMovies(apiKey = MOVIE_DB_API_KEY, pageNumber = pageNumber)

    override fun getTopRatedMovies(pageNumber: String) =
        movieRequests.getTopRatedMovies(apiKey = MOVIE_DB_API_KEY, pageNumber = pageNumber)

    override fun getMovieDetails(movieId: String) =
        movieRequests.getMovieDetails(movieId = movieId, apiKey = MOVIE_DB_API_KEY)
}