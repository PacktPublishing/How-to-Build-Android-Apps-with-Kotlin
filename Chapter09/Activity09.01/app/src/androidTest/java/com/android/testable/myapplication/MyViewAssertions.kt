package com.android.testable.myapplication

import androidx.test.espresso.ViewAssertion

fun checkRecyclerViewItems(count: Int): ViewAssertion {
    return RecyclerViewItemCountAssertion(count)
}