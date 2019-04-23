package com.intact.filmireview.data

import com.intact.filmireview.data.request.HomeRequests
import com.intact.filmireview.data.response.PopularMovieResponse
import com.intact.filmireview.util.MOVIE_DB_API_KEY
import io.reactivex.Single
import javax.inject.Inject

/**
 *  implementation of base data manager
 */

class DataManager @Inject constructor(private val homeRequests: HomeRequests) : BaseDataManager {

    override fun getPopularMovies(pageNumber: String) =
        homeRequests.getPopularMovies(MOVIE_DB_API_KEY, pageNumber)

    override fun getTopRatedMovies(pageNumber: String) =
        homeRequests.getTopRatedMovies(MOVIE_DB_API_KEY, pageNumber)
}