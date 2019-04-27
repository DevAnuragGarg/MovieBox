package com.intact.filmireview.data.request

import com.intact.filmireview.data.response.MovieResponse
import com.intact.filmireview.data.model.MovieDetailDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Anurag Garg on 19/03/19
 *
 * All the requests related to home screen are written here
 */

interface MovieRequests {

    @GET("movie/popular/")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: String
    ): Single<MovieResponse>

    @GET("movie/top_rated/")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: String
    ): Single<MovieResponse>

    @GET("movie/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String
    ): Single<MovieDetailDTO>
}