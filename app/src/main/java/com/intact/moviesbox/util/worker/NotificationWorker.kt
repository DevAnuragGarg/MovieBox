package com.intact.moviesbox.util.worker

import android.content.Context
import androidx.work.*
import com.google.common.util.concurrent.ListenableFuture
import com.intact.moviesbox.domain.usecases.GetNowPlayingMoviesUseCase
import com.intact.moviesbox.presentation.mapper.NowPlayingDomainPresentationMapper
import com.intact.moviesbox.util.INTENT_KEY_DAILY_NOTIFICATION
import com.intact.moviesbox.util.createAndShowNotification
import timber.log.Timber
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit


/**
 * a Worker defines what the work does, a WorkRequest defines how and
 * when work should be run.
 *
 * Accept inputs and produce outputs. Both inputs and outputs are
 * represented as key, value pairs. Always return a value representing
 * success, failure, or retry.
 *
 * The input and output are passed as Data, which is essentially a map
 * of primitive types and arrays.
 *
 * Result can be: Result.success(), Result.failure(), Result.retry()
 *
 * ou can specify a second interval that controls when your periodic Worker will be allowed to
 * run inside a portion of the repetition period. This second interval (the flexInterval) it’s
 * positioned at the end of the repetition interval itself.  Imagine you want to build a periodic
 * Work request with a 30 minutes period. You can specify a flexInterval, smaller than this period,
 * say a 15 minute flexInterval.
 * <!----------Interval 1-------><!----------Interval 2------->
 * <---15 min----><---15 min----><---15 min=---><---15 min---->
 * <--not work---><-----work----><--not work---><-----work---->
 *
 * We should be using RxWorker her and run like this
 * class MyRxWorker(context : Context, params : WorkerParameters) : RxWorker(context, params) {
 * val remoteService = RemoteService()
 * override fun createWork(): Single<Result> {
 *      return remoteService.getMySingleResponse()
 *          .doOnSuccess { /* process result somehow */ }
 *          .map { Result.success() }
 *          .onErrorReturn { Result.failure() }
 *      }
 * }
 * But this is for fetching the data using Single observer
 *
 * Sending the notification using work manager
 */

const val PERIODIC_NOTIFICATION_REPEAT_TIME: Long = 6
const val PERIODIC_NOTIFICATION_FLEX_INTERVAL: Long = 4
const val PERIODIC_NOTIFICATION_WORKER_TAG = "notification_worker_tag_periodic"
const val DAILY_NOTIFICATION_WORKER_TAG = "notification_worker_tag_daily"

class NotificationWorker(
    private val appContext: Context,
    workerParameters: WorkerParameters,
    private val nowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val nowPlayingDomainPresentationMapper: NowPlayingDomainPresentationMapper
) :
    Worker(appContext, workerParameters) {

    override fun doWork(): Result {
        Timber.d("NotificationWorker: doWork")

        // getting the boolean data
        val isDailyWorkerNotification = inputData.getBoolean(INTENT_KEY_DAILY_NOTIFICATION, false)

        // fetch data from server
        nowPlayingMoviesUseCase.buildUseCase(GetNowPlayingMoviesUseCase.Param("1"))
            .map { nowPlayingDomainPresentationMapper.to(it) }.subscribe({
                val movieDTO = it.movies[0]
                createAndShowNotification(appContext, movieDTO.title, movieDTO.overview)
            }, {
                createAndShowNotification(
                    appContext,
                    "Error: ${if (isDailyWorkerNotification) "Daily" else "Periodic"}",
                    it.localizedMessage
                )
            })

        // create the daily notification again
        if (isDailyWorkerNotification) {
            WorkManager.getInstance(appContext).enqueueUniqueWork(
                DAILY_NOTIFICATION_WORKER_TAG,
                ExistingWorkPolicy.KEEP,
                createDailyWorkRequest()
            )
        }

        return Result.success()
    }
}

/**
 * If you need to check already running work manager just because you don't want duplicate works.
 * You can use the tags to get the worker present in the queue or is it running
 *
 * In place of that you can use enqueueUniquePeriodicWork: This method allows you to enqueue a
 * uniquely-named PeriodicWorkRequest, where only one PeriodicWorkRequest of a particular name
 * can be active at a time. For example, you may only want one sync operation to be active.
 * If there is one pending, you can choose to let it run or replace it with your new work.
 */
fun isWorkerRunning(context: Context, tag: String): Boolean {
    Timber.d("isWorkerRunning")
    val workManager = WorkManager.getInstance(context)
    val status: ListenableFuture<List<WorkInfo>> = workManager.getWorkInfosByTag(tag)
    try {
        var running = true
        val workInfoList: List<WorkInfo> = status.get()

        for (workInfo in workInfoList) {
            val workInfoState = workInfo.state
            running =
                workInfoState == WorkInfo.State.RUNNING || workInfoState == WorkInfo.State.ENQUEUED
        }
        return running
    } catch (e: ExecutionException) {
        e.printStackTrace()
        return false
    } catch (e: InterruptedException) {
        e.printStackTrace()
        return false
    }
}

/**
 * using the work-manager to schedule the work later that is certain to happen but can be deferred
 * we can use the tag or using uniquePeriod work we can check if the work has been added to the queue
 * or not
 */
fun createPeriodicWorkRequest(): PeriodicWorkRequest {
    Timber.d("NotificationWorker: createPeriodicWorkRequest")
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    // workDataOf (part of KTX) converts a list of pairs to a [Data] object.
    val notificationData = workDataOf(
        INTENT_KEY_DAILY_NOTIFICATION to false
    )

    return PeriodicWorkRequest.Builder(
        NotificationWorker::class.java,
        PERIODIC_NOTIFICATION_REPEAT_TIME,
        TimeUnit.HOURS,
        PERIODIC_NOTIFICATION_FLEX_INTERVAL,
        TimeUnit.HOURS
    )
        .setInputData(notificationData)
        .setConstraints(constraints)
        .addTag(PERIODIC_NOTIFICATION_WORKER_TAG)
        .build()
}

/**
 * You can then have an execution at 5:00AM the first day, 5:25AM the second day, 5:15AM the third,
 * then 5:30AM the following one and so on. At the moment, if you need to execute a worker at roughly
 * the same time, every day, your best option is to use a OneTimeWorkRequest with an initial delay
 * so that you execute it at the right time:
 */
fun createDailyWorkRequest(): OneTimeWorkRequest {
    Timber.d("NotificationWorker: createDailyWorkRequest")
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    // workDataOf (part of KTX) converts a list of pairs to a [Data] object.
    val notificationData = workDataOf(
        INTENT_KEY_DAILY_NOTIFICATION to true
    )

    val currentDate = Calendar.getInstance()
    val dueDate = Calendar.getInstance()

    // Set Execution around 10:00:00 AM
    dueDate.set(Calendar.HOUR_OF_DAY, 10)
    dueDate.set(Calendar.MINUTE, 0)
    dueDate.set(Calendar.SECOND, 0)

    if (dueDate.before(currentDate)) {
        dueDate.add(Calendar.HOUR_OF_DAY, 24)
    }
    val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis
    return OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInputData(notificationData)
        .setConstraints(constraints)
        .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
        .addTag(DAILY_NOTIFICATION_WORKER_TAG)
        .build()
}