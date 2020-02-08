package com.android.testable.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId

class Activity1Robot {

    fun insertText(text: String): Activity1Robot {
        onView(withId(R.id.activity_1_edit_text)).perform(replaceText(text))
        return this
    }

    fun submit(): Activity1Robot {
        onView(withId(R.id.activity_1_button)).perform(click())
        return this
    }
}