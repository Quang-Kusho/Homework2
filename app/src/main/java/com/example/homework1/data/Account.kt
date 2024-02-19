package com.example.homework1.data

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name: String,
    val info: String,
    val imageData: Bitmap? = null,
    val imageDescription: String = ""
)