package com.example.colors

import org.junit.Test

import org.junit.Assert.*

class UtilitiesTest {

    @Test
    fun `given string passed in, when all valid hexadecimal characters, then returns true`() {

        val colorString = "00FF99"

        assertTrue(hasValidHexadecimalCharacters(colorString))
    }

    @Test
    fun `given string passed in, when there are some none hexadecimal characters, then returns false`() {

        val colorString = "0LFFY9"

        assertFalse(hasValidHexadecimalCharacters(colorString))
    }

    @Test
    fun `given string passed in, when there are too many hexadecimal characters, then returns false`() {

        val colorString = "00FF99FF"

        assertFalse(hasValidHexadecimalCharacters(colorString))
    }

    @Test
    fun `given string passed in, when there are too few hexadecimal characters, then returns false`() {

        val colorString = "00FF"

        assertFalse(hasValidHexadecimalCharacters(colorString))
    }

    @Test
    fun `given string string passed in, when empty, then returns false`() {

        val colorString = ""

        assertFalse(hasValidHexadecimalCharacters(colorString))
    }

    @Test
    fun `given string string passed in, when blank, then returns false`() {

        val colorString = "  "

        assertFalse(hasValidHexadecimalCharacters(colorString))
    }
}
