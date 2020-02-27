package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.entities.NowPlayingMoviesEntity
import com.intact.moviesbox.domain.repositories.MovieRepository
import com.intact.moviesbox.domain.usecases.base.ObservableUseCase
import com.intact.moviesbox.domain.usecases.base.SingleObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class NowPlayingMoviesUseCase constructor(
    @Inject private val movieRepository: MovieRepository, backgroundScheduler: Scheduler,
    foregroundScheduler: Scheduler
) :
    ObservableUseCase<NowPlayingMoviesEntity, NowPlayingMoviesUseCase.Param>(
        backgroundScheduler,
        foregroundScheduler
    ) {

    // this data class will hold the data which is required to
    data class Param(val pageNumber: String)

    override fun generateObservable(params: Param?): Observable<NowPlayingMoviesEntity> {
        if (params == null) {
            throw IllegalArgumentException("NowPlayingMoviesUseCase parameter can't be null")
        }
        return movieRepository.getTopRatedMovies(pageNumber = params.pageNumber)
    }
}