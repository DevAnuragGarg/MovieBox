package com.intact.filmireview.data

import com.intact.filmireview.data.request.HomeRequests
import com.intact.filmireview.util.MOVIE_DB_API_KEY
import javax.inject.Inject

/**
 * this class helps in providing the data from the server
 */

class DataManager @Inject constructor(private val homeRequests: HomeRequests) : BaseDataManager {

    override fun getPopularMovies(pageNumber: String) =
        homeRequests.getPopularMovies(MOVIE_DB_API_KEY, pageNumber)
}