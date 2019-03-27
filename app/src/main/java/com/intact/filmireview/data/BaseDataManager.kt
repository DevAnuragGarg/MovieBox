package com.intact.filmireview.data

import com.intact.filmireview.data.response.PopularMovieResponse
import io.reactivex.Single

interface BaseDataManager {

    fun getPopularMovies(pageNumber: String) : Single<PopularMovieResponse>
}