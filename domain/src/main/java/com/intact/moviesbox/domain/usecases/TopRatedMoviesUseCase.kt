package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.entities.MovieResponseEntity
import com.intact.moviesbox.domain.repositories.MovieRepository
import com.intact.moviesbox.domain.usecases.base.SingleObservableUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class TopRatedMoviesUseCase constructor(
    @Inject private val movieRepository: MovieRepository, backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler
) :
    SingleObservableUseCase<MovieResponseEntity, TopRatedMoviesUseCase.Param>(
        backgroundScheduler,
        foregroundScheduler
    ) {

    // this data class will hold the data which is required to
    data class Param(val pageNumber: String)

    override fun generateObservable(params: Param?): Single<MovieResponseEntity> {
        if (params == null) {
            throw IllegalArgumentException("PopularMoviesUseCase parameter can't be null")
        }
        return movieRepository.getTopRatedMovies(pageNumber = params.pageNumber)
    }
}