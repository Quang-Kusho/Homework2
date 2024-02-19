package com.example.homework1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: Account)

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)

    @Query("SELECT * from accounts WHERE id= :id")
    fun getProfile(id: Int): Account

    @Query("SELECT * from accounts ORDER BY name ASC")
    fun getAllProfiles(): Flow<List<Account>>
}