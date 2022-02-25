package com.ods.myunittests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ods.myunittests.data.SpendDatabase
import com.ods.myunittests.data.SpendTrackerDataSource
import com.ods.myunittests.utils.getOrAwaitValue
import com.ods.myunittests.viewmodels.SpendViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SpendViewModelTest : TestCase() {
    lateinit var viewModel: SpendViewModel

    @Before
    override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, SpendDatabase::class.java)
            .allowMainThreadQueries().build()
        val dataSource = SpendTrackerDataSource(db.getSpendDao())

        viewModel = SpendViewModel(dataSource)
    }

    @Test
    fun testSpendViewModel() {
        viewModel.addSpend(170, "bought something!")
        viewModel.setListOfSpend()

        val result = viewModel.getListOfSpends().getOrAwaitValue().find {
            it.amount == 170 && it.description == "bought something!"
        }
        assertThat(result != null).isTrue()
    }
}