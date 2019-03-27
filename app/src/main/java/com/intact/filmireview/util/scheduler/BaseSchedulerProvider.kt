package com.intact.filmireview.util.scheduler

import io.reactivex.Scheduler

interface BaseSchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

    fun network(): Scheduler

    fun background(): Scheduler

    fun trampoline(): Scheduler
}
