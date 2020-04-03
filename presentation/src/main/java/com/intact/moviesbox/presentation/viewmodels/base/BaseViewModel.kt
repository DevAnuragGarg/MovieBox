package com.intact.moviesbox.presentation.viewmodels.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Anurag Garg on 25/03/19.
 */
abstract class BaseViewModel : ViewModel() {

    fun getCompositeDisposable() = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        getCompositeDisposable().clear()
    }
}