package com.android.testable.myapplication

class MyInstrumentedApplication : MyApplication() {

    override fun createItemGenerator(): ItemGenerator {
        return ItemGenerator(timer, stringProvider, 0, countingIdlingResource)
    }
}