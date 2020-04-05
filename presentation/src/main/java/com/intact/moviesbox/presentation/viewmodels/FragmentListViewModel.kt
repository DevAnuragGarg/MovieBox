package com.intact.moviesbox.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import com.intact.moviesbox.domain.entities.MovieDomainDTO
import com.intact.moviesbox.domain.entities.NowPlayingMoviesDomainDTO
import com.intact.moviesbox.domain.entities.TopRatedMoviesDomainDTO
import com.intact.moviesbox.domain.entities.UpcomingMoviesDomainDTO
import com.intact.moviesbox.domain.usecases.GetNowPlayingMoviesUseCase
import com.intact.moviesbox.domain.usecases.GetTopRatedMoviesUseCase
import com.intact.moviesbox.domain.usecases.GetUpcomingMoviesUseCase
import com.intact.moviesbox.domain.usecases.SaveMovieDetailUseCase
import com.intact.moviesbox.presentation.mapper.Mapper
import com.intact.moviesbox.presentation.model.*
import com.intact.moviesbox.presentation.viewmodels.base.BaseViewModel
import timber.log.Timber
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

class FragmentListViewModel @Inject constructor(
    private val movieMapper: Mapper<MovieDomainDTO, MovieDTO>,
    private val saveMovieDetailUseCase: SaveMovieDetailUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val topRatedMoviesMapper: Mapper<TopRatedMoviesDomainDTO, TopRatedMoviesDTO>,
    private val upcomingMoviesMapper: Mapper<UpcomingMoviesDomainDTO, UpcomingMoviesDTO>,
    private val nowPlayingMoviesMapper: Mapper<NowPlayingMoviesDomainDTO, NowPlayingMoviesDTO>
) : BaseViewModel() {

    private val isLoading = MutableLiveData<Boolean>()
    private val topRatedErrorLiveData = MutableLiveData<ErrorDTO>()
    private val upcomingErrorLiveData = MutableLiveData<ErrorDTO>()
    private val loadingProgressLiveData = MutableLiveData<Boolean>()
    private val nowPlayingErrorLiveData = MutableLiveData<ErrorDTO>()
    private val topRatedMoviesLiveData = MutableLiveData<ArrayList<MovieDTO>>()
    private val upcomingMoviesLiveData = MutableLiveData<ArrayList<MovieDTO>>()
    private val nowPlayingMoviesLiveData = MutableLiveData<ArrayList<MovieDTO>>()

    // get now playing movies
    fun getNowPlayingMovies(pageNumber: String) {
        isLoading.value = true
        getCompositeDisposable().add(
            getNowPlayingMoviesUseCase.buildUseCase(GetNowPlayingMoviesUseCase.Param(pageNumber = pageNumber))
                .doOnSubscribe {
                    // only for the first page we are asking for update
                    if (pageNumber == "1") {
                        loadingProgressLiveData.value = true
                    }
                }
                .doOnTerminate {
                    // only for the first page we are asking for update
                    if (pageNumber == "1") {
                        loadingProgressLiveData.value = false
                    }
                }
                .map { nowPlayingMoviesMapper.to(it) }
                .subscribe({ it ->
                    Timber.d("Success: Now playing movies response received: ${it.movies}")
                    nowPlayingMoviesLiveData.value = it.movies
                }, {
                    nowPlayingErrorLiveData.value =
                        ErrorDTO(code = 400, message = it.localizedMessage)
                })
        )
    }

    // get the top rated movies
    fun getTopRatedMovies(pageNumber: String) {
        isLoading.value = true
        getCompositeDisposable().add(
            getTopRatedMoviesUseCase.buildUseCase(GetTopRatedMoviesUseCase.Param(pageNumber = pageNumber))
                .doOnSubscribe {
                    // only for the first page we are asking for update
                    if (pageNumber == "1") {
                        loadingProgressLiveData.value = true
                    }
                }
                .doOnTerminate {
                    // only for the first page we are asking for update
                    if (pageNumber == "1") {
                        loadingProgressLiveData.value = false
                    }
                }
                .map { topRatedMoviesMapper.to(it) }
                .subscribe({ it ->
                    Timber.d("Success: Top rated movies response received: ${it.movies}")
                    topRatedMoviesLiveData.value = it.movies
                }, {
                    topRatedErrorLiveData.value =
                        ErrorDTO(code = 400, message = it.localizedMessage)
                })
        )
    }

    // get the upcoming movies
    fun getUpcomingMovies(pageNumber: String) {
        isLoading.value = true
        getCompositeDisposable().add(
            getUpcomingMoviesUseCase.buildUseCase(GetUpcomingMoviesUseCase.Param(pageNumber = pageNumber))
                .doOnSubscribe {
                    // only for the first page we are asking for update
                    if (pageNumber == "1") {
                        loadingProgressLiveData.value = true
                    }
                }
                .doOnTerminate {
                    // only for the first page we are asking for update
                    if (pageNumber == "1") {
                        loadingProgressLiveData.value = false
                    }
                }
                .map { upcomingMoviesMapper.to(it) }
                .subscribe({
                    Timber.d("Success: Upcoming movies response received: ${it.movies}")
                    upcomingMoviesLiveData.value = it.movies
                }, {
                    upcomingErrorLiveData.value =
                        ErrorDTO(code = 400, message = it.localizedMessage)
                })
        )
    }

    // save the movie detail
    fun saveMovieDetail(movieDTO: MovieDTO) {
        getCompositeDisposable().add(
            saveMovieDetailUseCase.buildUseCase(
                SaveMovieDetailUseCase.Param(movieDomainDTO = movieMapper.from(movieDTO))
            ).subscribe({
                Timber.d("Movie successfully saved in DB")
            }, {
                Timber.d("Error while saving: ${it.localizedMessage}")
            }
            )
        )
    }

    fun getTopRatedErrorLiveData() = topRatedErrorLiveData
    fun getUpcomingErrorLiveData() = upcomingErrorLiveData
    fun getNowPlayingErrorLiveData() = nowPlayingErrorLiveData
    fun getLoadingProgressLiveData() = loadingProgressLiveData
    fun getTopRatedMoviesListLiveData() = topRatedMoviesLiveData
    fun getUpcomingMoviesListLiveData() = upcomingMoviesLiveData
    fun getNowPlayingMoviesListLiveData() = nowPlayingMoviesLiveData

//    val topRatedMoviesLiveData: LiveData<Resource<NowPlayingMoviesModel>>
//        get() = nowPlayingMoviesUseCase
//            .buildUseCase(NowPlayingMoviesUseCase.Param("1"))
//            .map { nowPlayingMoviesMapper.to(it) }
//            .map { Resource.success(it) }
//            .startWith(Resource.loading())
//            .onErrorResumeNext(
//                Function {
//                    Observable.just(Resource.error(it.localizedMessage))
//                }
//            )
//            .toFlowable(BackpressureStrategy.LATEST)    // not working with single observable
//            .toLiveData()
}