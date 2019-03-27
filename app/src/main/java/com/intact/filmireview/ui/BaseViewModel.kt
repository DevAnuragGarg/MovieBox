package com.intact.filmireview.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intact.filmireview.data.BaseDataManager
import com.intact.filmireview.util.scheduler.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Anurag Garg on 25/03/19.
 */
abstract class BaseViewModel(
    private val dataManager: BaseDataManager,
    private val disposable: CompositeDisposable,
    private val schedulerProvider: BaseSchedulerProvider
) : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun getLoadingLiveData() = isLoading
    fun getBaseDataManager() = dataManager
    fun getCompositeDisposable() = disposable
    fun getBaseSchedulerProvider() = schedulerProvider
}