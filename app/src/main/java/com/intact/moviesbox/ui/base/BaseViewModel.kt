package com.intact.moviesbox.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Anurag Garg on 25/03/19.
 */
abstract class BaseViewModel(
    private val disposable: CompositeDisposable,
    private val schedulerProvider: BaseSchedulerProvider
) : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun getLoadingLiveData() = isLoading
    fun getCompositeDisposable() = disposable
    fun getBaseSchedulerProvider() = schedulerProvider
}