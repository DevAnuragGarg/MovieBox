package com.intact.moviesbox.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.intact.moviesbox.domain.entities.MovieResponseEntity
import com.intact.moviesbox.domain.usecases.PopularMoviesUseCase
import com.intact.moviesbox.domain.usecases.TopRatedMoviesUseCase
import com.intact.moviesbox.presentation.mapper.Mapper
import com.intact.moviesbox.presentation.model.MovieDataResponseModel
import com.intact.moviesbox.presentation.model.Resource
import com.intact.moviesbox.presentation.viewmodels.base.BaseViewModel
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
 *
 * If you want an Observable to emit a specific sequence of items before it
 * begins emitting the items normally expected from it, apply the StartWith
 * operator to it.
 *
 * handling the error cases in rx can be checked at
 * https://github.com/ReactiveX/RxJava/wiki/Error-Handling-Operators
 * doOnError, onErrorComplete, onErrorResumeNext, onErrorReturn, onErrorReturnItem
 * onExceptionResumeNext, retry, retryUntil, retryWhen
 */

class HomeViewModel @Inject constructor(
    private val movieDataResponseMapper: Mapper<MovieResponseEntity, MovieDataResponseModel>,
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase
) : BaseViewModel() {

    val popularMovieLiveData: LiveData<Resource<MovieDataResponseModel>>
        get() = popularMoviesUseCase
            .buildUseCase(PopularMoviesUseCase.Param("1"))
            .map { movieDataResponseMapper.to(it) }
            .map { Resource.success(it) }
            .startWith(Resource.loading())
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.error(it.localizedMessage))
                }
            )
            .toFlowable(BackpressureStrategy.LATEST)    // not working with single observable
            .toLiveData()

//    val topRatedMovies: LiveData<Resource<MovieDataResponseModel>>
//        get() = topRatedMoviesUseCase
//            .buildUseCase(TopRatedMoviesUseCase.Param("1"))
//            .map { movieDataResponseMapper.to(it) }
//            .map { Resource.success(it) }

}