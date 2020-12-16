package com.example.loginactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

const val USER_NAME_KEY = "USER_NAME_KEY"
const val PASSWORD_KEY = "PASSWORD_KEY"

const val IS_LOGGED_IN = "IS_LOGGED_IN"
const val LOGGED_IN_USERNAME = "LOGGED_IN_USERNAME"

//This is done as an example for simplicity and user/pwd credentials should never be stored in an app
const val USER_NAME_CORRECT_VALUE = "someusername"
const val PASSWORD_CORRECT_VALUE = "somepassword"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit_button.setOnClickListener {

            val userName = (user_name as EditText).text.toString().trim()
            val passWord = (password as EditText).text.toString().trim()

            hideKeyboard()

            if (userName.isNotEmpty() && passWord.isNotEmpty()) {

                //Set the name of the activity to launch
                Intent(this, WelcomeActivity::class.java).also { welcomeIntent ->
                    //Add the data
                    welcomeIntent.putExtra(USER_NAME_KEY, userName)
                    welcomeIntent.putExtra(PASSWORD_KEY, passWord)

                    //Reset text fields to blank
                    user_name.text.clear()
                    password.text.clear()

                    //Launch
                    startActivity(welcomeIntent)
                }

            } else {
                val toast = Toast.makeText(this, getString(R.string.login_form_entry_error), Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }

        }
    }

    private fun hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}
