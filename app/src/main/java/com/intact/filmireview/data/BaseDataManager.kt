package com.intact.filmireview.data

import com.intact.filmireview.data.response.MovieResponse
import com.intact.filmireview.data.model.MovieDetailDTO
import io.reactivex.Single

/**
 * Base data manager interface having all the functions listed to fetch the
 * data from server or database
 */

interface BaseDataManager {

    fun getPopularMovies(pageNumber: String): Single<MovieResponse>

    fun getTopRatedMovies(pageNumber: String): Single<MovieResponse>

    fun getMovieDetails(movieId: String): Single<MovieDetailDTO>
}