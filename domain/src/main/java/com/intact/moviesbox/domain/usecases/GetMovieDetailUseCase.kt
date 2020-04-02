package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.domain.repositories.MovieRepository
import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import com.intact.moviesbox.domain.usecases.base.SingleObservableUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    baseSchedulerProvider: BaseSchedulerProvider
) : SingleObservableUseCase<MovieDomainDTO, GetMovieDetailUseCase.Param>(baseSchedulerProvider) {

    class Param(val movieId: Long)

    override fun generateObservable(params: Param?): Single<MovieDomainDTO> {
        if (params == null) {
            throw IllegalArgumentException("GetMovieDetailUseCase parameter can't be null")
        }
        return movieRepository.getMovieDetail(movieId = params.movieId)
    }
}