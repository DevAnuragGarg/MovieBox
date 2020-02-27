package com.intact.moviesbox.data.repository

import com.intact.moviesbox.data.model.MovieData
import com.intact.moviesbox.data.model.NowPlayingMoviesData
import io.reactivex.Observable
import io.reactivex.Single

interface RemoteDataSource {
    fun getRunningNowMovies(pageNumber: String): Observable<NowPlayingMoviesData>
    fun getTopRatedMovies(pageNumber: String): Observable<NowPlayingMoviesData>
    fun getUpcomingMovies()
    fun getPopularMovies(pageNumber: String): Observable<NowPlayingMoviesData>
    fun getTrendingMovies()
    fun getMovieDetails(movieId: Long): Single<MovieData>
}