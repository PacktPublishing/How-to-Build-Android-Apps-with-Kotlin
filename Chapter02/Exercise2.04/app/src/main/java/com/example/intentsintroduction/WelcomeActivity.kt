package com.example.intentsintroduction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        //Get the intent which started this activity
        intent?.let {

            //Set the welcome message
            val fullName = it.getStringExtra(FULL_NAME_KEY)
            welcome_text.text = getString(R.string.welcome_text, fullName)

        }


    }
}
