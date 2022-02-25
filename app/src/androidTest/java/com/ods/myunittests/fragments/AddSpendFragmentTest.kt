package com.ods.myunittests.fragments

import android.annotation.SuppressLint
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ods.myunittests.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern

@RunWith(AndroidJUnit4::class)
class AddSpendFragmentTest : TestCase() {

    private lateinit var scenario: FragmentScenario<AddSpendFragment>

    @Before
    override fun setUp() {
        super.setUp()
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_MyUnitTests)
        scenario.moveToState(Lifecycle.State.STARTED)
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
        assertThat(onView(withId(R.id.textResult)).check(matches(ViewMatchers.withText("Spend added!"))))
    }
}