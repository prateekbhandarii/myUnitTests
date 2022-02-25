package com.ods.myunittests.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Spend::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class SpendDatabase : RoomDatabase() {

    abstract fun getSpendDao(): SpendDao

    companion object {
        private const val DATABASE_NAME = "Spending-Database.db"

        private var database: SpendDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) {
            if (database == null) {
                synchronized(LOCK) {
                    database = buildDatabase(context)
                }
            }
        }

        private fun buildDatabase(context: Context): SpendDatabase {
            return Room.databaseBuilder(context, SpendDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration().build()
        }
    }
}