package com.intact.moviesbox.presentation.util

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Executor

/**
 * Caused by: java.lang.RuntimeException: Method getMainLooper in android.os.Looper not mocked.
 * See http://g.co/androidstudio/not-mocked for details.
 *
 * the reason behind is that your interactor is trying to execute the client call in a different
 * thread than the main thread. During a test execution we are actually not interested in this
 * behaviour, we just want to make sure that the client is called and mapped back to our UI model
 */

class RxImmediateSchedulerRule : TestRule {
    override fun apply(base: Statement, d: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
                RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}