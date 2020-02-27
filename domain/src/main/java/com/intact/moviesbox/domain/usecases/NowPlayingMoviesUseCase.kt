package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.entities.NowPlayingMoviesEntity
import com.intact.moviesbox.domain.repositories.MovieRepository
import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import com.intact.moviesbox.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Now playing movies use case to fetch the playing now movies
 * Extending the {@ObservableUseCase} and implementing the
 * generateObservable using the @MovieRepository interface
 */
class NowPlayingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    schedulerProvider: BaseSchedulerProvider
) : ObservableUseCase<NowPlayingMoviesEntity, NowPlayingMoviesUseCase.Param>(schedulerProvider) {

    // this data class will hold the data which is required to
    data class Param(val pageNumber: String)

    override fun generateObservable(params: Param?): Observable<NowPlayingMoviesEntity> {
        if (params == null) {
            throw IllegalArgumentException("NowPlayingMoviesUseCase parameter can't be null")
        }
        return movieRepository.getTopRatedMovies(pageNumber = params.pageNumber)
    }
}