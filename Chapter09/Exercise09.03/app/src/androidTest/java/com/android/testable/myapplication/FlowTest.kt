package com.android.testable.myapplication

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FlowTest {

    @JvmField
    @Rule
    var activityRule: ActivityTestRule<Activity1> = ActivityTestRule(Activity1::class.java)
    private val myApplication = getApplicationContext<MyApplication>()

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(myApplication.countingIdlingResource)
    }

    @Test
    fun verifyFlow() {
        onView(withId(R.id.activity_1_button)).perform(click())
        onView(withId(R.id.activity_2_text_view)).check(matches(withText(myApplication.getString(R.string.opened_after_x_seconds, 1))))
    }
}