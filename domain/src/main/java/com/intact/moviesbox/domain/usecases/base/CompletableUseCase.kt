package com.intact.moviesbox.domain.usecases.base

import io.reactivex.Completable
import io.reactivex.Scheduler

/**
 * base class for use case to create completable observables
 */
abstract class CompletableUseCase<in Param> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {

    protected abstract fun generateCompletable(params: Param? = null): Completable

    /**
     * function to generate the custom completable with params
     * @param params parameters to generate completable observable
     * subscribing on background scheduler
     * observing on foreground scheduler
     */
    fun buildUseCase(params: Param? = null) =
        generateCompletable(params)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
}