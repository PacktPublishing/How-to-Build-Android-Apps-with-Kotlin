package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.enter_button)?.setOnClickListener {
            //Get the greeting display text
            val greetingDisplay = findViewById<TextView>(R.id.greeting_display)

            //Get the first name TextInputEditText value
            val firstName = findViewById<TextInputEditText>(R.id.first_name)?.text.toString().trim()

            //Get the last name TextInputEditText value
            val lastName = findViewById<TextInputEditText>(R.id.last_name)?.text.toString().trim()

            //Check names are not empty here:
            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {

                val nameToDisplay = firstName.plus(" ").plus(lastName)
                //Use Kotlin's string templates feature to display the name
                greetingDisplay?.text =
                    " ${getString(R.string.welcome_to_the_app)} ${nameToDisplay}!"
            } else {
                Toast.makeText(this, getString(R.string.please_enter_a_name), Toast.LENGTH_LONG)
                    .apply {
                        setGravity(Gravity.CENTER, 0, 0)
                        show()
                    }
            }
        }
    }
}
