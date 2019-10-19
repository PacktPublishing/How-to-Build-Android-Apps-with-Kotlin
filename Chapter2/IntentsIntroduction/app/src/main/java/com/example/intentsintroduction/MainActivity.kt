package com.example.intentsintroduction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

const val FULL_NAME_KEY = "FULL_NAME_KEY"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit_button.setOnClickListener {

            val fullName = (full_name as EditText).text.toString()

            if (fullName.isNotBlank()) {

                //Set the name of the activity to launch
                Intent(this, WelcomeActivity::class.java).also { welcomeIntent ->
                    //Add the data
                    welcomeIntent.putExtra(FULL_NAME_KEY, fullName)
                    //Launch
                    startActivity(welcomeIntent)
                }

            } else {
                Toast.makeText(this, getString(R.string.full_name_label), Toast.LENGTH_LONG).show()
            }

        }
    }
}
