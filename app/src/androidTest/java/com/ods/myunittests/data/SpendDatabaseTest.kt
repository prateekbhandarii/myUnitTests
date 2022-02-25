package com.ods.myunittests.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class SpendDatabaseTest : TestCase() {

    lateinit var db: SpendDatabase
    lateinit var dao: SpendDao

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>();
        db = Room.inMemoryDatabaseBuilder(context, SpendDatabase::class.java).build()
        dao = db.getSpendDao()
    }

    @After
    fun closeDatabase() {
        db.close()
    }

    @Test
    fun readAndWriteSpending() = runBlocking {
        val date = Date()
        val spend = Spend(date, 100, "bought something")
        dao.addSpend(spend)

        val spends = dao.getLast20Spends()
        assertThat(spends.contains(spend)).isTrue()
    }
}