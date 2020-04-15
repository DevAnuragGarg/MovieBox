package com.intact.moviesbox.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.intact.moviesbox.R
import com.intact.moviesbox.ui.activity.HomeActivity

// class to create the notifications
const val CHANNEL_ID = "primary_notification_channel"

/**
 * Before you can deliver the notification on Android 8.0 and higher, you must register your app's
 * notification channel with the system by passing an instance of NotificationChannel to
 * createNotificationChannel(). Because you must create the notification channel before posting any
 * notifications on Android 8.0 and higher, you should execute this code as soon as your app starts
 *
 * Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new
 * and not in the support library
 */
fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = context.getString(R.string.channel_name)
        val descriptionText = context.getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun createAndShowNotification(context: Context, textTitle: String, textContent: String) {
    // Create an explicit intent for an Activity in your app
    val intent = Intent(context, HomeActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setContentIntent(pendingIntent)
        .setStyle(NotificationCompat.BigTextStyle().bigText(textContent))

    builder.priority = NotificationCompat.PRIORITY_HIGH

    // show the notification
    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
}