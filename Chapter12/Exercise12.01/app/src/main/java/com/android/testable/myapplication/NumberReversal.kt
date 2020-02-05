package com.android.testable.myapplication

class NumberReversal {

    fun reverseNumber(n: Int): Int {
        var num = n
        var reversed = 0
        while (num != 0) {
            val digit = num % 10
            reversed = reversed * 10 + digit
            num /= 10
        }
        return reversed
    }
}