package com.example.homework1.ui

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import com.example.homework1.data.AccountDatabase


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = Room.databaseBuilder(
                applicationContext,
                AccountDatabase::class.java, "accounts_database"
            ).build()
            val notificationService = NotificationService(applicationContext)
            val notificationChannel = NotificationChannel(
                "notification_theme",
                "Changing Theme",
                NotificationManager.IMPORTANCE_HIGH
            )
            with(NotificationManagerCompat.from(applicationContext)) {
                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        android.Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED)  {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        REQUEST_CODE
                    )
                }
            }
            notificationService.notiManager.createNotificationChannel(notificationChannel)
            val accountDao = db.accountDao()
            val viewModel = AppViewModel(accountDao, notificationService)
            AccountApp(viewModel = viewModel)
        }
    }
}
