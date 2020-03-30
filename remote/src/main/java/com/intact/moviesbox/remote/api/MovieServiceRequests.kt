package com.intact.moviesbox.remote.api

import com.intact.moviesbox.remote.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * All the requests related to movies are written here
 */

interface MovieServiceRequests {

    @GET("movie/now_playing/")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: String
    ): Observable<NowPlayingMoviesDTONetwork>

    @GET("movie/popular/")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: String
    ): Observable<PopularMoviesDTONetwork>

    @GET("movie/top_rated/")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: String
    ): Observable<TopRatedMoviesDTONetwork>

    @GET("trending/movie/day/")
    fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: String
    ): Observable<TrendingMoviesDTONetwork>

    @GET("movie/upcoming/")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: String
    ): Observable<UpcomingMoviesDTONetwork>

    @GET("movie/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String
    ): Single<MovieDTONetwork>
}