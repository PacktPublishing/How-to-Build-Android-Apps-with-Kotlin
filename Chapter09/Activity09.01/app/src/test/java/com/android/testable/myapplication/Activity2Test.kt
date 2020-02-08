package com.android.testable.myapplication

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@MediumTest
@Config(shadows = [InstantItemGenerator::class])
@RunWith(AndroidJUnit4::class)
class Activity2Test {

    @JvmField
    @Rule
    val rule = IntentsTestRule(Activity2::class.java, false, false)
    private val itemCount = 5

    @Before
    fun setUp() {
        rule.launchActivity(Intent().putExtra(Activity2.EXTRA_ITEM_COUNT, itemCount))
    }

    @LooperMode(LooperMode.Mode.PAUSED)
    @Test
    fun `test click opens activity 3`() {
        val position = 3
        val itemText = rule.activity.getString(R.string.item_x, (position + 1))
        onView(withText(itemText)).check(matches(isDisplayed()))
        onView(withId(R.id.activity_2_recycler_view)).perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        onView(withId(R.id.activity_2_recycler_view)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
        intended(allOf(hasComponent(hasShortClassName(".Activity3")), hasExtra(Activity3.EXTRA_ITEM, Item(itemText))))

    }
}