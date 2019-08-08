package com.intact.filmireview.domain.repositories

import com.intact.filmireview.domain.entities.MovieDetailEntity
import com.intact.filmireview.domain.entities.MovieResponseEntity
import io.reactivex.Single

/**
 * Base data repository interface having all the functions
 * listed to fetch the data from server or database
 */

interface BaseRepository {

    fun getPopularMovies(pageNumber: String): Single<MovieResponseEntity>

    fun getTopRatedMovies(pageNumber: String): Single<MovieResponseEntity>

    fun getMovieDetails(movieId: String): Single<MovieDetailEntity>
}