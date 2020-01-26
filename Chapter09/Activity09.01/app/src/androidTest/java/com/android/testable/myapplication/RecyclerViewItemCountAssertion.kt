package com.android.testable.myapplication

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.junit.Assert.assertEquals

class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (view is RecyclerView) {
            val adapter = view.adapter
            assertEquals(expectedCount, adapter!!.itemCount)
        }
    }
}