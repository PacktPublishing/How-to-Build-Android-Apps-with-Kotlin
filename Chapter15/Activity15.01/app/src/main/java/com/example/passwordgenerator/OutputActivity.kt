package com.example.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar

class OutputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_output)

        val length: Int = intent?.getStringExtra("length")?.toInt() ?: 0
        val upperCase: Boolean = intent?.getBooleanExtra("uppercase", false) ?: false
        val numbers: Boolean = intent?.getBooleanExtra("numbers", false) ?: false
        val special: Boolean = intent?.getBooleanExtra("special", false) ?: false

        val password1: TextView = findViewById(R.id.password1_text)
        password1.text = generatePassword(
            length = length,
            addUpperCase = upperCase,
            addNumbers = numbers,
            addSpecial = special
        )
        val password2: TextView = findViewById(R.id.password2_text)
        password2.text = generatePassword(
            length = length,
            addUpperCase = upperCase,
            addNumbers = numbers,
            addSpecial = special
        )
        val password3: TextView = findViewById(R.id.password3_text)
        password3.text = generatePassword(
            length = length,
            addUpperCase = upperCase,
            addNumbers = numbers,
            addSpecial = special
        )
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            val password = when {
                password1.isVisible -> {
                    password1.text.toString()
                }
                password2.isVisible -> {
                    password2.text.toString()
                }
                else -> {
                    password3.text.toString()
                }
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