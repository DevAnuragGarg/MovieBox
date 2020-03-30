package com.intact.moviesbox.domain.repositories

import com.intact.moviesbox.domain.entities.*
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Base data repository interface having all the functions
 * listed to fetch the data from server or database
 * this is the contract set by domain layer for the data layer
 */

interface MovieRepository {

    fun getPopularMovies(pageNumber: String): Observable<PopularMoviesEntity>

    fun getTopRatedMovies(pageNumber: String): Observable<TopRatedMoviesEntity>

    fun getTrendingMovies(pageNumber: String): Observable<TrendingMoviesEntity>

    fun getPlayingNowMovies(pageNumber: String): Observable<NowPlayingMoviesEntity>

    fun getUpcomingRatedMovies(pageNumber: String): Observable<UpcomingMoviesEntity>

    fun getMovieDetails(movieId: Long): Single<MovieEntity>
}