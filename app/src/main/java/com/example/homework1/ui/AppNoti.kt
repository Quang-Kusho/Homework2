package com.example.homework1.ui

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat


const val PRIORITY_HIGH = NotificationCompat.PRIORITY_HIGH
const val PRIORITY_DEFAULT = NotificationCompat.PRIORITY_DEFAULT
const val PRIORITY_LOW = NotificationCompat.PRIORITY_LOW
const val PRIORITY_MAX = NotificationCompat.PRIORITY_MAX

const val REQUEST_CODE = 200
class NotificationService(private val context: Context) {

    val notiManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val intent = Intent(context, MainActivity::class.java)
    private val pendingIntent = PendingIntent.getActivity(
        context,
        REQUEST_CODE,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )


    fun showNotification(
        id: Int,
        channelID: String,
        launcherForegroundID: Int,
        title: String,
        text: String,
        priority: Int,
        bigText: String = ""
    ) {
        val notification = NotificationCompat
            .Builder(context, channelID)
            .setSmallIcon(launcherForegroundID)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(priority)
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            .build()
        notiManager.notify(id, notification)
    }
}
