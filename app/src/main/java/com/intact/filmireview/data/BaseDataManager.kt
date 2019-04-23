package com.intact.filmireview.data

import com.intact.filmireview.data.response.PopularMovieResponse
import io.reactivex.Single

/**
 * Base data manager interface having all the functions listed to fetch the
 * data from server or database
 */

interface BaseDataManager {

    fun getPopularMovies(pageNumber: String) : Single<PopularMovieResponse>

    fun getTopRatedMovies(pageNumber: String): Single<PopularMovieResponse>
}