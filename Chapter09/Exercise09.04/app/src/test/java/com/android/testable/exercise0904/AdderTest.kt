package com.android.testable.exercise0904

import org.junit.Test

import org.junit.Assert.*

class AdderTest {

    private val adder = Adder()

    @Test
    fun sumSuccess() {
        assertEquals(1, adder.sum(1))
        assertEquals(3, adder.sum(2))
        assertEquals(55, adder.sum(10))
        assertEquals(210, adder.sum(20))
        assertEquals(2305843008139952128L, adder.sum(Int.MAX_VALUE))
    }

    @Test
    fun sumError(){
        assertEquals(-1, adder.sum(0))
        assertEquals(-1, adder.sum(-1))
    }
}