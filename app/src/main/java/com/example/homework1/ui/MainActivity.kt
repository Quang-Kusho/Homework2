package com.example.homework1.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.homework1.data.AccountDatabase


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val db = Room.databaseBuilder(
                applicationContext,
                AccountDatabase::class.java, "accounts_database"
            ).build()

            val accountDao = db.accountDao()
            val viewModel = AppViewModel(accountDao)
            AccountApp(viewModel = viewModel)
        }
    }
}
