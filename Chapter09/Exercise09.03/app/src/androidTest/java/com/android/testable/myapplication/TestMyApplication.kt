package com.android.testable.myapplication

import java.util.*

class TestMyApplication : MyApplication() {

    override fun createRandomizer(): Randomizer {
        return TestRandomizer(Random())
    }
}