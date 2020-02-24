package com.intact.moviesbox.ui.movieDetail

import androidx.lifecycle.MutableLiveData
import com.intact.moviesbox.data.BaseDataManager
import com.intact.moviesbox.ui.BaseViewModel
import com.intact.moviesbox.data.model.ErrorDTO
import com.intact.moviesbox.data.model.MovieDetailDTO
import com.intact.moviesbox.util.scheduler.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Anurag Garg on 2019-04-24.
 */
class MovieDetailViewModel @Inject constructor(
    dataManager: BaseDataManager,
    disposable: CompositeDisposable,
    schedulerProvider: BaseSchedulerProvider
) : BaseViewModel(dataManager, disposable, schedulerProvider) {

    private val errorLiveData = MutableLiveData<ErrorDTO>()
    private val movieDetailLiveData = MutableLiveData<MovieDetailDTO>()

    fun getMovieDetails(id: String) {
        getLoadingLiveData().value = true
        getCompositeDisposable().add(
            getBaseDataManager().getMovieDetails(id)
                .subscribeOn(getBaseSchedulerProvider().io())
                .observeOn(getBaseSchedulerProvider().ui())
                .subscribe({ it ->
                    Timber.d("Success: Movie detail response received: $it")
                    movieDetailLiveData.value = it
                }, {
                    val error = it as HttpException
                    Timber.d("ErrorCode: ${error.code()} & Failure: ${error.localizedMessage}")
                    errorLiveData.value = ErrorDTO(code = error.code(), message = error.localizedMessage)
                })
        )
    }

    fun getMovieDetailLiveData() = movieDetailLiveData
    fun getErrorLiveData() = errorLiveData
}