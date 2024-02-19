package com.example.homework1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Account::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AccountDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao
}