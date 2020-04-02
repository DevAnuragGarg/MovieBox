package com.intact.moviesbox.domain.schedulers

import io.reactivex.Scheduler

/**
 * this is the base scheduler having the different types of schedulers
 * the main implementation will be in the di package of the app module
 */
interface BaseSchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

    fun network(): Scheduler

    fun background(): Scheduler

    fun trampoline(): Scheduler

    fun computation(): Scheduler
}
