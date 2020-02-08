package com.android.testable.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule

@RunWith(AndroidJUnit4::class)
class MainActivityUiTest {

    @JvmField
    @Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun showSumResultInTextView() {

        activityRule.activity.let { activity ->
            onView(withId(R.id.edit_text)).perform(replaceText("5"))
            onView(withId(R.id.button)).perform(click())
            onView(withId(R.id.text_view)).check(matches(withText(activity.getString(R.string.the_sum_of_numbers_from_1_to_is, 5, "15"))))
        }
    }

    @Test
    fun showErrorInTextView() {
        activityRule.activity.let { activity ->
            onView(withId(R.id.edit_text)).perform(replaceText("-5"))
            onView(withId(R.id.button)).perform(click())
            onView(withId(R.id.text_view)).check(matches(withText(activity.getString(R.string.error_invalid_number))))
        }
    }
}