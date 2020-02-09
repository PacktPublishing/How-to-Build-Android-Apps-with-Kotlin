package com.android.testable.myapplication

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @JvmField
    @Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testDisplaysPosts() {
        onView(withText("Title 1")).check(matches(isDisplayed()))
        onView(withText("Body 1")).check(matches(isDisplayed()))
        onView(withText("Title 2")).check(matches(isDisplayed()))
        onView(withText("Body 2")).check(matches(isDisplayed()))
        onView(withText("Title 3")).check(matches(isDisplayed()))
        onView(withText("Body 3")).check(matches(isDisplayed()))
    }
}