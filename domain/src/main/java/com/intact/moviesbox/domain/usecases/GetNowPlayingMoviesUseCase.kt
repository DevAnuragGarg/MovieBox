package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.entities.NowPlayingMoviesDomainDTO
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
class GetNowPlayingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    schedulerProvider: BaseSchedulerProvider
) : ObservableUseCase<NowPlayingMoviesDomainDTO, GetNowPlayingMoviesUseCase.Param>(schedulerProvider) {

    // this data class will hold the data which is required to
    data class Param(val pageNumber: String)

    override fun generateObservable(params: Param?): Observable<NowPlayingMoviesDomainDTO> {
        if (params == null) {
            throw IllegalArgumentException("NowPlayingMoviesUseCase parameter can't be null")
        }
        return movieRepository.getPlayingNowMovies(pageNumber = params.pageNumber)
    }
}