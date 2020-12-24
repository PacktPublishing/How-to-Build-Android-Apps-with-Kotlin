package com.example.passwordgenerator

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lengthText: EditText = findViewById(R.id.length_text)
        val uppercaseCheckbox: CheckBox = findViewById(R.id.uppercase_check)
        val numberCheckbox: CheckBox = findViewById(R.id.number_check)
        val specialCheckbox: CheckBox = findViewById(R.id.special_check)

        val button: Button = findViewById(R.id.generate_button)
        button.setOnClickListener {
            val length = lengthText.text.toString().toInt()
            if (length < 6 || length > 20) {
                Snackbar.make(it, "Length must be from 6 to 20", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, OutputActivity::class.java).apply {
                putExtra("length", lengthText.text.toString())
                putExtra("uppercase", uppercaseCheckbox.isChecked)
                putExtra("numbers", numberCheckbox.isChecked)
                putExtra("special", specialCheckbox.isChecked)
            }
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }
}