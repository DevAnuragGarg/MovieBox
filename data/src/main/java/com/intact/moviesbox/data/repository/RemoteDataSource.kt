package com.intact.moviesbox.data.repository

import com.intact.moviesbox.data.model.*
import io.reactivex.Observable
import io.reactivex.Single

/**
 * interface implemented by the remote data source
 * contract between data layer and remote data source
 */
interface RemoteDataSource {
    fun getMovieDetails(movieId: Long): Single<MovieData>
    fun getPopularMovies(pageNumber: String): Observable<PopularMoviesData>
    fun getTrendingMovies(pageNumber: String): Observable<TrendingMoviesData>
    fun getUpcomingMovies(pageNumber: String): Observable<UpcomingMoviesData>
    fun getTopRatedMovies(pageNumber: String): Observable<TopRatedMoviesData>
    fun getRunningNowMovies(pageNumber: String): Observable<NowPlayingMoviesData>
}