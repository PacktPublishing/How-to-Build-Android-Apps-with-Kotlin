package com.android.testable.myapplication

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

class Activity2Robot {
    private val myApplication = ApplicationProvider.getApplicationContext<MyApplication>()

    fun verifyItemNumber(expected: Int): Activity2Robot {
        onView(withId(R.id.activity_2_recycler_view)).check(checkRecyclerViewItems(expected))
        return this
    }

    fun verifyItemText(itemPosition: Int): Activity2Robot {
        onView(withText(myApplication.getString(R.string.item_x, (itemPosition + 1)))).check(matches(ViewMatchers.isDisplayed()))
        return this
    }

    fun clickOnItem(itemPosition: Int): Activity2Robot {
        onView(withId(R.id.activity_2_recycler_view)).perform(scrollToPosition<RecyclerView.ViewHolder>(itemPosition))
        onView(withId(R.id.activity_2_recycler_view)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(itemPosition, click()))
        return this
    }
}