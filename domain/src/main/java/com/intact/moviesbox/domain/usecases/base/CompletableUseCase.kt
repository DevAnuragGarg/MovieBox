package com.intact.moviesbox.domain.usecases.base

import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import io.reactivex.Completable

/**
 * base class for use case to create completable observables.
 * this kind of use case is just to know the completable status of the operation
 */
abstract class CompletableUseCase<in Param> constructor(private val schedulerProvider: BaseSchedulerProvider) {
    protected abstract fun generateCompletable(params: Param? = null): Completable

    /**
     * function to generate the custom completable with params
     * @param params parameters to generate completable observable
     * subscribing on background scheduler
     * observing on foreground scheduler
     */
    fun buildUseCase(params: Param? = null) =
        generateCompletable(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
}