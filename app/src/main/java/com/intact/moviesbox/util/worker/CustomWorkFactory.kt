package com.intact.moviesbox.util.worker

import androidx.work.DelegatingWorkerFactory
import com.intact.moviesbox.domain.usecases.GetNowPlayingMoviesUseCase
import javax.inject.Inject
import javax.inject.Singleton

/**
 * we use a DelegatingWorkerFactory to coordinate all the single factories.
 * Instead of configuring WorkManager to directly use our factory, we can use a
 * DelegatingWorkerFactory and add to it our own WorkerFactory using its addFactory() method. You
 * can then have multiple factories where each one takes care of one or more workers. Register your
 * factory with the DelegatingWorkerFactory and it will take care to coordinate the multiple factories.
 *
 */
@Singleton
class CustomWorkFactory @Inject constructor(
    nowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : DelegatingWorkerFactory() {
    init {
        addFactory(NotificationWorkerFactory(nowPlayingMoviesUseCase))
    }
}