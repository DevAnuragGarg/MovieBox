package com.intact.filmireview.domain.repositories

import com.intact.filmireview.domain.entities.MovieDetailEntity
import com.intact.filmireview.domain.entities.MovieResponseEntity
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Base data repository interface having all the functions
 * listed to fetch the data from server or database
 */

interface MovieRepository {

    fun getPopularMovies(pageNumber: String): Observable<MovieResponseEntity>

    fun getTopRatedMovies(pageNumber: String): Single<MovieResponseEntity>

    fun getMovieDetails(movieId: String): Single<MovieDetailEntity>
}