package com.example.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_output.*

class OutputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output)

        val length: Int = intent?.getStringExtra("length")?.toInt() ?: 0
        val upperCase: Boolean = intent?.getBooleanExtra("uppercase", false) ?: false
        val numbers: Boolean = intent?.getBooleanExtra("numbers", false) ?: false
        val special: Boolean = intent?.getBooleanExtra("special", false) ?: false

        password1_text.text = generatePassword(
            length = length,
            addUpperCase = upperCase,
            addNumbers = numbers,
            addSpecial = special
        )
        password2_text.text = generatePassword(
            length = length,
            addUpperCase = upperCase,
            addNumbers = numbers,
            addSpecial = special
        )
        password3_text.text = generatePassword(
            length = length,
            addUpperCase = upperCase,
            addNumbers = numbers,
            addSpecial = special
        )

        button.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            val password = if (password1_text.isVisible) {
                password1_text.text.toString()
            } else if (password2_text.isVisible) {
                password2_text.text.toString()
            } else {
                password3_text.text.toString()
            }

            val clip: ClipData = ClipData.newPlainText("password", password)
            clipboard.setPrimaryClip(clip)

            Snackbar.make(it, "Password has been copied!", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun generatePassword(
        length: Int,
        addUpperCase: Boolean,
        addNumbers: Boolean,
        addSpecial: Boolean
    ): String {
        val password = mutableListOf<Char>()

        val lowercaseCharacters = ('a'..'z').toList()
        val upperCaseCharacters = lowercaseCharacters.map { it.toUpperCase() }
        val numbers = ('0'..'9').toList()
        val specialCharacters = "~!@#$%^&*()_+-="

        val characters = mutableListOf<Char>()
        characters.addAll(lowercaseCharacters)
        if (addUpperCase) {
            characters.addAll(upperCaseCharacters)
            password.add(upperCaseCharacters.random())
        }

        if (addNumbers) {
            characters.addAll(numbers)
            password.add(numbers.random())
        }

        if (addSpecial) {
            val specials = specialCharacters.toCharArray().toList()
            characters.addAll(specials)
            password.add(specials.random())
        }

        while (password.size < length) {
            password.add(characters.random())
        }

        password.shuffle()

        return password.joinToString("")
    }

}