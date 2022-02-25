package com.ods.myunittests.activity

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.ods.myunittests.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testAddAndViewSpend() {
        onView(withId(R.id.button_add_spend)).perform(click())
        testAddAndViewSpend()

    }

    @SuppressLint("CheckResult")
    @Test
    fun testAddingSpend() {
        val amount = 100
        val desc = "Temp description"
        onView(withId(R.id.etAmount)).perform(typeText(amount.toString()))
        onView(withId(R.id.etDescription)).perform(typeText(desc))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btnSave)).perform(click())
        onView(withText(amount.toString())).check(matches(isDisplayed()))
        onView(withText(desc)).check(matches(isDisplayed()))
    }
}