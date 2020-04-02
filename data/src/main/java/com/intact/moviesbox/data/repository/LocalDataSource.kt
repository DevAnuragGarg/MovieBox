package com.intact.moviesbox.data.repository

import com.intact.moviesbox.data.model.MovieDataDTO
import io.reactivex.Completable
import io.reactivex.Single

/**
 * functions to be implemented by the local data source implementer
 * act as a contract between the local data source implementer and data layer
 */
interface LocalDataSource {

    fun getMovieDetail(movieId: Long): Single<MovieDataDTO>

    fun saveMovieDetail(movieDataDTO: MovieDataDTO): Completable

    //fun saveMovieDetails(movieDataDTO: List<MovieDataDTO>): Completable
}