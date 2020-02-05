package com.android.testable.myapplication

import java.util.*

class Randomizer(private val random: Random) {

    fun generateNumber(): Int = random.nextInt(999900) + 100
}