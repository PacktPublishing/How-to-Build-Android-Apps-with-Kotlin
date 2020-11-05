package com.example.myapplication

import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set a click listener on the button so we can respond to the button click
        enter_button.setOnClickListener {
            //Set the text to an empty string when the button is pressed initially
            greeting_display.text = ""

            //Get the first name TextInputEditText value
            val firstName = (first_name as EditText).text.toString()

            //Get the last name TextInputEditText value
            val lastName = (last_name as EditText).text.toString()

            //Check the names are not blank trimming any leading or trailing spaces
            if (firstName.trim().isNotBlank() && lastName.trim().isNotBlank()) {

                //Prepare the name to display
                val nameToDisplay = firstName.plus(" ").plus(lastName)

                //Use Kotlin's string templates feature to display the name
                greeting_display.text =
                    " ${getString(R.string.welcome_to_the_app)} ${nameToDisplay}!"
            } else {

                val toast =
                    Toast.makeText(this, getString(R.string.please_enter_a_name), Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }
    }
}



