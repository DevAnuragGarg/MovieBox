package com.intact.moviesbox.domain.repositories

import com.intact.moviesbox.domain.entities.MovieEntity
import com.intact.moviesbox.domain.entities.NowPlayingMoviesEntity
import com.intact.moviesbox.domain.entities.TopRatedMoviesEntity
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Base data repository interface having all the functions
 * listed to fetch the data from server or database
 * this is the contract set by domain layer for the data layer
 */

interface MovieRepository {

    fun getPlayingNowMovies(pageNumber: String): Observable<NowPlayingMoviesEntity>

    fun getPopularMovies(pageNumber: String): Observable<NowPlayingMoviesEntity>

    fun getTopRatedMovies(pageNumber: String): Observable<TopRatedMoviesEntity>

    fun getMovieDetails(movieId: Long): Single<MovieEntity>
}