package com.intact.filmireview.data.request

import com.intact.filmireview.data.response.PopularMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Anurag Garg on 19/03/19
 *
 * All the requests related to home screen are written here
 */

interface HomeRequests {

    @GET("movie/popular/")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: String
    ): Single<PopularMovieResponse>
}