package com.intact.filmireview.ui.home

import androidx.lifecycle.MutableLiveData
import com.intact.filmireview.data.BaseDataManager
import com.intact.filmireview.data.model.ErrorDTO
import com.intact.filmireview.data.model.MovieDTO
import com.intact.filmireview.ui.BaseViewModel
import com.intact.filmireview.util.scheduler.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject


/**
 * view models don't care about the source of data. They are only
 * dependable on the observables handed over by use cases. View models
 * don't have any idea how to get and set the data. View models convert these
 * observables into live data using live data reactive streams and expose
 * only live data
 */

class HomeViewModel @Inject constructor(
    dataManager: BaseDataManager,
    compositeDisposable: CompositeDisposable,
    schedulerProvider: BaseSchedulerProvider
) : BaseViewModel(dataManager, compositeDisposable, schedulerProvider) {

    private val errorLiveData = MutableLiveData<ErrorDTO>()
    private val popularMoviesLiveData = MutableLiveData<ArrayList<MovieDTO>>()
    private val topRatedMoviesLiveData = MutableLiveData<ArrayList<MovieDTO>>()

    // get the popular movies
    fun getPopularMovies() {
        getLoadingLiveData().value = true
        getCompositeDisposable().add(
            getBaseDataManager().getPopularMovies("1")
                .subscribeOn(getBaseSchedulerProvider().io())
                .observeOn(getBaseSchedulerProvider().ui())
                .subscribe({ it ->
                    Timber.d("Success: Popular movies response received: ${it.movies}")
                    popularMoviesLiveData.value = it.movies
                }, {
                    val error = it as HttpException
                    Timber.d("ErrorCode: ${error.code()} & Failure: ${error.localizedMessage}")
                    errorLiveData.value = ErrorDTO(code = error.code(), message = error.localizedMessage)
                })
        )
    }

    // get the popular movies
    fun getTopRatedMovies() {
        getLoadingLiveData().value = true
        getCompositeDisposable().add(
            getBaseDataManager().getTopRatedMovies("1")
                .subscribeOn(getBaseSchedulerProvider().io())
                .observeOn(getBaseSchedulerProvider().ui())
                .subscribe({ it ->
                    Timber.d("Success: Top rated movies response received: ${it.movies}")
                    topRatedMoviesLiveData.value = it.movies
                }, {
                    val error = it as HttpException
                    Timber.d("ErrorCode: ${error.code()} & Failure: ${error.localizedMessage}")
                    errorLiveData.value = ErrorDTO(code = error.code(), message = error.localizedMessage)
                })
        )
    }

    fun getErrorLiveData() = errorLiveData
    fun getPopularMoviesLiveData() = popularMoviesLiveData
    fun getTopRatedMoviesLiveData() = topRatedMoviesLiveData

    override fun onCleared() {
        super.onCleared()
        getCompositeDisposable().clear()
    }
}