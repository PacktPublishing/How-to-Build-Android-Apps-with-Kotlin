package com.android.testable.myapplication

import androidx.test.espresso.idling.CountingIdlingResource
import java.util.*

class Synchronizer(
    private val randomizer: Randomizer,
    private val timer: Timer,
    private val countingIdlingResource: CountingIdlingResource
) {

    fun executeAfterDelay(callback: (Int) -> Unit) {
        val timeToWait = randomizer.getTimeToWait()
        countingIdlingResource.increment()
        timer.schedule(CallbackTask(callback, timeToWait), timeToWait * 1000L)
    }


    inner class CallbackTask(
        private val callback: (Int) -> Unit,
        private val time: Int
    ) : TimerTask() {
        override fun run() {
            callback.invoke(time)
            countingIdlingResource.decrement()
        }

    }
}