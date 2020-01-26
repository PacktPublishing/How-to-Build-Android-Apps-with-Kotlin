package com.android.testable.myapplication

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UiTest {

    @JvmField
    @Rule
    var activityRule: ActivityTestRule<Activity1> = ActivityTestRule(Activity1::class.java)
    private val myApplication = ApplicationProvider.getApplicationContext<MyApplication>()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(myApplication.countingIdlingResource)
    }

    @Test
    fun testMyFlow() {
        val numberOfItems = 5

        Activity1Robot()
            .insertText(numberOfItems.toString())
            .submit()

        val selectedPosition = 3
        Activity2Robot()
            .verifyItemNumber(numberOfItems)
            .verifyItemText(selectedPosition)
            .clickOnItem(selectedPosition)

        val expectedTest = myApplication.getString(R.string.item_x, (selectedPosition + 1))
        Activity3Robot()
            .verifyText(expectedTest)
    }

}