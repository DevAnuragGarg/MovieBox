package com.intact.moviesbox.remote.api

import com.intact.moviesbox.remote.model.MovieDTONetwork
import com.intact.moviesbox.remote.model.NowPlayingMoviesDTONetwork
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
    ): Observable<NowPlayingMoviesDTONetwork>

    @GET("movie/top_rated/")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: String
    ): Observable<NowPlayingMoviesDTONetwork>

    @GET("movie/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String
    ): Single<MovieDTONetwork>
}