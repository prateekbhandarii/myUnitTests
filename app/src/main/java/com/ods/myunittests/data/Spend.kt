package com.ods.myunittests.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "spending")
data class Spend(
    val date: Date,
    val amount: Int,
    val description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}