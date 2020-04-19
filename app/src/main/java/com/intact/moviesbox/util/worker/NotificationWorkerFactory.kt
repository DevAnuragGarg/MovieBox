package com.intact.moviesbox.util.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.intact.moviesbox.domain.usecases.GetNowPlayingMoviesUseCase
import com.intact.moviesbox.presentation.mapper.NowPlayingDomainPresentationMapper
import timber.log.Timber

/**
 * as we can't directly inject the params in the worker as it is being called by
 * work manager. To solve this worker factory is created: A factory object that creates
 * ListenableWorker instances. The factory is invoked every time a work runs
 *
 * here also we do not use the dagger inject, but we will create the DelegatingWorkerFactory
 * to coordinate all the single factories
 */
class NotificationWorkerFactory(
    private val nowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val nowPlayingDomainPresentationMapper: NowPlayingDomainPresentationMapper
) :
    WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        Timber.d("Create Worker")
        return when (workerClassName) {
            NotificationWorker::class.java.name ->
                NotificationWorker(
                    appContext,
                    workerParameters,
                    nowPlayingMoviesUseCase,
                    nowPlayingDomainPresentationMapper
                )
            else ->
                // Return null, so that the base class can delegate to the default WorkerFactory.
                null
        }
    }
}