package com.intact.filmireview.data

import com.intact.filmireview.data.request.HomeRequests
import com.intact.filmireview.util.MOVIE_DB_API_KEY
import javax.inject.Inject

/**
 *  implementation of base data manager
 */

class DataManager @Inject constructor(private val homeRequests: HomeRequests) : BaseDataManager {

    override fun getPopularMovies(pageNumber: String) =
        homeRequests.getPopularMovies(MOVIE_DB_API_KEY, pageNumber)
}