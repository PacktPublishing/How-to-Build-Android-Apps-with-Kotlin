package com.android.testable.myapplication

import androidx.test.espresso.idling.CountingIdlingResource
import java.util.*

open class ItemGenerator(
    private val timer: Timer,
    private val stringProvider: StringProvider,
    private val initialDelay: Long,
    private val countingIdlingResource: CountingIdlingResource
) {

    open fun generateItemsAsync(itemCount: Int, callback: (List<Item>) -> Unit) {
        countingIdlingResource.increment()
        timer.schedule(ItemGeneratorTask(itemCount, callback), initialDelay)
    }

    internal fun generateItems(itemCount: Int): List<Item> {
        val result = mutableListOf<Item>()
        for (i in 1..itemCount) {
            result.add(Item(stringProvider.provideItemString(i)))
        }
        return result
    }


    inner class ItemGeneratorTask(
        private val itemCount: Int,
        private val callback: (List<Item>) -> Unit
    ) : TimerTask() {
        override fun run() {
            callback.invoke(generateItems(itemCount))
            countingIdlingResource.decrement()
        }

    }
}