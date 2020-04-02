package com.intact.moviesbox.domain.repositories

import com.intact.moviesbox.domain.entities.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Base data repository interface having all the functions
 * listed to fetch the data from server or database
 * this is the contract set by domain layer for the data layer
 */

interface MovieRepository {

    fun getMovieDetail(movieId: Long): Single<MovieDomainDTO>

    fun saveMovieDetail(movieDetail:MovieDomainDTO): Completable

    //fun saveAllMovies(movieDetailList: List<MovieDomainDTO>): Completable

    fun getPopularMovies(pageNumber: String): Observable<PopularMoviesDomainDTO>

    fun getTopRatedMovies(pageNumber: String): Observable<TopRatedMoviesDomainDTO>

    fun getTrendingMovies(pageNumber: String): Observable<TrendingMoviesDomainDTO>

    fun getPlayingNowMovies(pageNumber: String): Observable<NowPlayingMoviesDomainDTO>

    fun getUpcomingRatedMovies(pageNumber: String): Observable<UpcomingMoviesDomainDTO>
}