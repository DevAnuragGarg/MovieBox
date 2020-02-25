package com.intact.moviesbox.domain.usecases.base

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class ObservableUseCase<T, in Param> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {

    protected abstract fun generateObservable(params: Param? = null): Observable<T>

    /**
     * function to generate the custom observable with params
     * @param params parameters to generate observable
     * subscribing on background scheduler
     * observing on foreground scheduler
     */
    fun buildUseCase(params: Param? = null) =
        generateObservable(params)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
}