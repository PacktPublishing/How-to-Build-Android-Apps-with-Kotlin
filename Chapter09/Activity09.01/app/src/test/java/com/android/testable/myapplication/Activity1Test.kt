package com.android.testable.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra

import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class Activity1Test {

    @JvmField
    @Rule
    val rule = IntentsTestRule(Activity1::class.java)

    @Test
    fun `test enter number and submit`() {
        onView(withId(R.id.activity_1_edit_text)).perform(replaceText(5.toString()))
        onView(withId(R.id.activity_1_button)).perform(click())
        intended(allOf(hasComponent(hasShortClassName(".Activity2")), hasExtra(Activity2.EXTRA_ITEM_COUNT, 5)))
    }
}