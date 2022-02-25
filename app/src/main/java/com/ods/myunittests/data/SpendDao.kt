package com.ods.myunittests.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SpendDao {

    @Insert
    suspend fun addSpend(spend: Spend)

    @Query("SELECT * FROM spending ORDER BY date DESC LIMIT 20")
    suspend fun getLast20Spends(): List<Spend>
}