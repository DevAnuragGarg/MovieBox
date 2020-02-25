package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.entities.MovieResponseEntity
import com.intact.moviesbox.domain.repositories.MovieRepository
import com.intact.moviesbox.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler
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

    /**
     * data class used as a input to fetch the list of popular movies
     */
    data class Param(
        val pageNumber: String
    )
}