package com.intact.moviesbox.domain.usecases.base

import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import io.reactivex.Single

/**
 * base class for use case to create Single observables.
 * this kind of use case is just to return one time observable i.e. SingleObservable
 */
abstract class SingleObservableUseCase<T, in Param> constructor(private val schedulerProvider: BaseSchedulerProvider) {
    protected abstract fun generateObservable(params: Param? = null): Single<T>

    /**
     * function to generate the custom observable with params
     * @param params parameters to generate observable
     * subscribing on background scheduler
     * observing on foreground scheduler
     */
    fun buildUseCase(params: Param? = null) =
        generateObservable(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
}