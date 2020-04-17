package com.intact.moviesbox.util.worker

import android.content.Context
import androidx.work.*
import com.intact.moviesbox.util.SHOW_POPULAR_MOVIES
import com.intact.moviesbox.util.createAndShowNotification

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
 * Sending the notification using work manager
 */

class NotificationWorker(private val appContext: Context, workerParameters: WorkerParameters) :
    Worker(appContext, workerParameters) {

    override fun doWork(): Result {
        val title = inputData.getString(SHOW_POPULAR_MOVIES) ?: "Title not found"
        val description = inputData.getString(SHOW_POPULAR_MOVIES) ?: "Description not found"
        createAndShowNotification(appContext, title, description)
        return Result.success()
    }

    fun workRequestCode() {

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true)
            .setRequiresDeviceIdle(true)
            .build()

        // workDataOf (part of KTX) converts a list of pairs to a [Data] object.
        //val imageData = workDataOf(SHOW_POPULAR_MOVIES to "ABC")

//        val uploadWorkRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
//            .setInputData(imageData)
//            .setConstraints(constraints)
//            .build()
    }
}