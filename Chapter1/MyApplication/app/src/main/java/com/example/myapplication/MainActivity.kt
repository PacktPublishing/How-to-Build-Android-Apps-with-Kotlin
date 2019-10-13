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

        //Get the first name TextInputEditText value using findViewById
        //val firstName = findViewById<EditText>(R.id.first_name).text

        //Get the first name TextInputEditText value
        val firstName = (first_name as EditText).text
        //Get the last name TextInputEditText value
        val lastName = (last_name as EditText).text

        //Set a click listener on the button so we can respond to the button click
        enter_button.setOnClickListener {
            //Set the text to an empty string when the button is pressed initially

            //Check the names are not blank trimming any leading or trailing spaces
            if (firstName.trim().isNotBlank() && lastName.trim().isNotBlank()) {

                //Create the greeting followed by the full name
                val nameWithGreeting = StringBuilder()
                nameWithGreeting.append(getString(R.string.welcome_to_the_app))
                nameWithGreeting.append(" ")
                nameWithGreeting.append(firstName)
                nameWithGreeting.append(" ")
                nameWithGreeting.append(lastName)
                nameWithGreeting.append("!")

                //Sets the greeting_display text
                greeting_display.text = nameWithGreeting.toString()

            } else {
                //Displays a pop-up message informing the user they haven't entered the name correctly
                val toast =
                    Toast.makeText(this, getString(R.string.please_enter_a_name), Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()

            }
        }
    }
}



