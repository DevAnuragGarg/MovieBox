package com.intact.moviesbox.data.repository

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.NowPlayingMoviesData
import com.intact.moviesbox.data.model.TopRatedMoviesData
import io.reactivex.Observable
import io.reactivex.Single

/**
 * interface implemented by the remote data source
 * contract between data layer and remote data source
 */
interface RemoteDataSource {
    fun getRunningNowMovies(pageNumber: String): Observable<NowPlayingMoviesData>
    fun getTopRatedMovies(pageNumber: String): Observable<TopRatedMoviesData>
    fun getUpcomingMovies()
    fun getPopularMovies(pageNumber: String): Observable<NowPlayingMoviesData>
    fun getTrendingMovies()
    fun getMovieDetails(movieId: Long): Single<MovieData>
}