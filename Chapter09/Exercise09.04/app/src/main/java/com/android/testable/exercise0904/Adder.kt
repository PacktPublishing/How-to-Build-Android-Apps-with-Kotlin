package com.android.testable.exercise0904

class Adder {

    fun sum(n: Int): Long {
        return if (n > 0) (n * (n.toLong() + 1)) / 2 else -1
    }
}