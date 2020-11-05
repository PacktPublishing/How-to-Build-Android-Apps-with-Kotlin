package com.android.testable.myapplication

import android.app.Application
import androidx.test.espresso.idling.CountingIdlingResource
import java.util.*

open class MyApplication : Application() {

    val countingIdlingResource = CountingIdlingResource("Timer resource")
    val timer = Timer()
    lateinit var stringProvider: StringProvider
    lateinit var itemGenerator: ItemGenerator

    override fun onCreate() {
        super.onCreate()
        stringProvider = StringProvider(this)
        itemGenerator = createItemGenerator()
    }

    protected open fun createItemGenerator(): ItemGenerator = ItemGenerator(timer, stringProvider, 1000, countingIdlingResource)
}