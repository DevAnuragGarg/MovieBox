package com.intact.filmireview.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.intact.filmireview.domain.entities.MovieResponseEntity
import com.intact.filmireview.domain.usecases.PopularMoviesUseCase
import com.intact.filmireview.presentation.mapper.Mapper
import com.intact.filmireview.presentation.model.MovieDataResponseModel
import com.intact.filmireview.presentation.model.Resource
import com.intact.filmireview.presentation.viewmodels.base.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

/**
 * view models don't care about the source of data. They are only
 * dependable on the observables handed over by use cases. View models
 * don't have any idea how to get and set the data. View models convert these
 * observables into live data using live data reactive streams and expose
 * only live data
 */

class HomeViewModel @Inject constructor(
    private val movieDataResponseMapper: Mapper<MovieResponseEntity, MovieDataResponseModel>,
    private val popularMoviesUseCase: PopularMoviesUseCase
) : BaseViewModel() {

    val popularMovieLiveData: LiveData<Resource<MovieDataResponseModel>>
        get() = popularMoviesUseCase
            .buildUseCase(PopularMoviesUseCase.Param("1"))
            .map { movieDataResponseMapper.to(it) }
            .map { Resource.success(it) }
            .startWith(Resource.loading())              // not working with single observable
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.error(it.localizedMessage))
                }
            )
            .toFlowable(BackpressureStrategy.LATEST)    // not working with single observable
            .toLiveData()
}