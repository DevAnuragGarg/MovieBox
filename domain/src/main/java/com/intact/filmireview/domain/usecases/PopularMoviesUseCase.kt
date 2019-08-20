package com.intact.filmireview.domain.usecases

import com.intact.filmireview.domain.entities.MovieResponseEntity
import com.intact.filmireview.domain.repositories.BaseMovieRepository
import com.intact.filmireview.domain.usecases.base.SingleObservableUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(
    private val baseMovieRepository: BaseMovieRepository,
    foregroundScheduler: Scheduler,
    backgroundScheduler: Scheduler
) : SingleObservableUseCase<MovieResponseEntity, PopularMoviesUseCase.Param>(backgroundScheduler, foregroundScheduler) {

    /**
     * implementing the base function to generate single observable
     * an overriding function does not allow to provide default value to parameters
     */
    override fun generateSingleObservable(params: Param?): Single<MovieResponseEntity> {
        if (params == null) {
            throw IllegalArgumentException("PopularMoviesUseCase parameter can't be null")
        }
        return baseMovieRepository.getPopularMovies(pageNumber = params.pageNumber)
    }

    data class Param(
        val pageNumber: String
    )
}