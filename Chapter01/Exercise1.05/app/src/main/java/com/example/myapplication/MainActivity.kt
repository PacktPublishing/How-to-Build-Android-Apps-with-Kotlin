package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enter_button.setOnClickListener {
            //Set the text to an empty string when the button is pressed initially
            greeting_display.text = ""

            //Get the first name TextInputEditText value
            val firstName = first_name.text.toString().trim()

            //Get the last name TextInputEditText value
            val lastName = last_name.text.toString().trim()

            //Check names are not empty here:
            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {

                val nameToDisplay = firstName.plus(" ").plus(lastName)
                //Use Kotlin's string templates feature to display the name
                greeting_display.text =
                    " ${getString(R.string.welcome_to_the_app)} ${nameToDisplay}!"
            } else {

            Toast.makeText(this, getString(R.string.please_enter_a_name), Toast.LENGTH_LONG).
                apply{
                    setGravity(Gravity.CENTER, 0, 0)
                    show()
                }
            }
        }
    }
}
