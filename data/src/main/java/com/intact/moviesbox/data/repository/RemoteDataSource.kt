package com.intact.moviesbox.data.repository

import com.intact.moviesbox.data.model.*
import io.reactivex.Observable
import io.reactivex.Single

/**
 * interface implemented by the remote data source
 * contract between data layer and remote data source
 */
interface RemoteDataSource {
    fun getMovieDetails(movieId: Long): Single<MovieDataDTO>
    fun getPopularMovies(pageNumber: String): Observable<PopularMoviesDataDTO>
    fun getTrendingMovies(pageNumber: String): Observable<TrendingMoviesDataDTO>
    fun getUpcomingMovies(pageNumber: String): Observable<UpcomingMoviesDataDTO>
    fun getTopRatedMovies(pageNumber: String): Observable<TopRatedMoviesDataDTO>
    fun getRunningNowMovies(pageNumber: String): Observable<NowPlayingMoviesDataDTO>
}