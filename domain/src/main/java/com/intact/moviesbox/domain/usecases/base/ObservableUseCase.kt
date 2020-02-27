package com.intact.moviesbox.domain.usecases.base

import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import io.reactivex.Observable

/**
 * base class for use case to create Single observables.
 * this kind of use case is just to return one time observable i.e. SingleObservable
 */
abstract class ObservableUseCase<T, in Param> constructor(private val schedulerProvider: BaseSchedulerProvider) {
    protected abstract fun generateObservable(params: Param? = null): Observable<T>

    /**
     * function to generate the custom observable with params
     * @param params parameters to generate observable
     * subscribing on background scheduler
     * observing on foreground scheduler
     */
    fun buildUseCase(params: Param? = null): Observable<T> =
        generateObservable(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
}