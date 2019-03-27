package com.intact.filmireview.util.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class SchedulerProvider : BaseSchedulerProvider {

    override fun computation() = Schedulers.computation()

    override fun io() = Schedulers.io()

    override fun ui() = AndroidSchedulers.mainThread()

    override fun network() = Schedulers.from(Executors.newCachedThreadPool())

    override fun background() = Schedulers.from(Executors.newCachedThreadPool())

    override fun trampoline() = Schedulers.trampoline()
}