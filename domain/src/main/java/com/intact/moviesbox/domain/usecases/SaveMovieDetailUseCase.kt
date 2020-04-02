package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.domain.repositories.MovieRepository
import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import com.intact.moviesbox.domain.usecases.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class SaveMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    baseSchedulerProvider: BaseSchedulerProvider
) : CompletableUseCase<SaveMovieDetailUseCase.Param>(baseSchedulerProvider) {

    //class Param(val movieDomainDTOs: List<MovieDomainDTO>)
    class Param(val movieDomainDTO: MovieDomainDTO)

    override fun generateCompletable(params: Param?): Completable {
        if (params == null) {
            throw IllegalArgumentException("SaveMovieDetailUseCase parameter can't be null")
        }
        //return movieRepository.saveMovieDetail(movieDetailList = params.movieDomainDTOs)
        return movieRepository.saveMovieDetail(movieDetail = params.movieDomainDTO)
    }
}