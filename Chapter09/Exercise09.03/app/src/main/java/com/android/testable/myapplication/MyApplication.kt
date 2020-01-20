package com.android.testable.myapplication

import android.app.Application
import androidx.test.espresso.idling.CountingIdlingResource
import java.util.*

open class MyApplication : Application() {

    val countingIdlingResource = CountingIdlingResource("Timer resource")
    lateinit var synchronizer: Synchronizer

    override fun onCreate() {
        super.onCreate()
        synchronizer = Synchronizer(createRandomizer(), Timer(), countingIdlingResource)

    }

    open fun createRandomizer() = Randomizer(Random())
}