package com.intact.filmireview.domain.usecases.base

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleObservableUseCase<T, in Param> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {

    protected abstract fun generateSingleObservable(params: Param? = null): Single<T>

    /**
     * function to generate the custom single observable with params
     * @param params parameters to generate single observable
     * subscribing on background scheduler
     * observing on foreground scheduler
     */
    fun buildUseCase(params: Param? = null) =
        generateSingleObservable(params)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
}