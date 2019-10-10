package com.example.colors


val hexadecimalCharacters = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
    "A", "B","C", "D", "E", "F", "a", "b", "c", "d", "e", "f")

const val HEXADECIMAL_STRING_LENGTH = 6

/**
 * Top Level Function to validate Hexadecimal characters
 */
fun hasValidHexadecimalCharacters(colorCharacters: String) : Boolean {

    if (colorCharacters.isBlank()) return false

    if (colorCharacters.trim().length != HEXADECIMAL_STRING_LENGTH) return false

    colorCharacters?.toUpperCase().let {it ->
        for (i in it) {
            if (hexadecimalCharacters.contains(i.toString())) continue
            return false
        }
    }

    return true
}
