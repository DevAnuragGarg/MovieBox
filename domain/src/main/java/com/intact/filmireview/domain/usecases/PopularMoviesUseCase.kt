package com.intact.filmireview.domain.usecases

import com.intact.filmireview.domain.entities.MovieResponseEntity
import com.intact.filmireview.domain.repositories.MovieRepository
import com.intact.filmireview.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    foregroundScheduler: Scheduler,
    backgroundScheduler: Scheduler
) : ObservableUseCase<MovieResponseEntity, PopularMoviesUseCase.Param>(
    backgroundScheduler,
    foregroundScheduler
) {

    /**
     * implementing the base function to generate observable
     * an overriding function does not allow to provide default value to parameters
     */
    override fun generateObservable(params: Param?): Observable<MovieResponseEntity> {
        if (params == null) {
            throw IllegalArgumentException("PopularMoviesUseCase parameter can't be null")
        }
        return movieRepository.getPopularMovies(pageNumber = params.pageNumber)
    }

    data class Param(
        val pageNumber: String
    )
}