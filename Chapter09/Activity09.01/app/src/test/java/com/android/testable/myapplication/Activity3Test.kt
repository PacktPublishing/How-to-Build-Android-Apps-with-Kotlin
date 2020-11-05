package com.android.testable.myapplication

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class Activity3Test {

    @JvmField
    @Rule
    val rule = IntentsTestRule(Activity3::class.java, false, false)

    private val item = Item("Text to display")

    @Before
    fun setUp() {
        rule.launchActivity(Intent().putExtra(Activity3.EXTRA_ITEM, item))
    }

    @Test
    fun `test displays correct text`() {
        onView(withId(R.id.activity_3_text_view)).check(matches(withText(rule.activity.getString(R.string.you_clicked_y, item.text))))
    }
}