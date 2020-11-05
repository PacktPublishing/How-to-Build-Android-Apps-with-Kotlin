package com.android.testable.myapplication

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

class Activity3Robot {

    private val myApplication = ApplicationProvider.getApplicationContext<MyApplication>()

    fun verifyText(expectedItemText: String): Activity3Robot {
        onView(withId(R.id.activity_3_text_view)).check(matches(withText(myApplication.getString(R.string.you_clicked_y, expectedItemText))))
        return this
    }
}