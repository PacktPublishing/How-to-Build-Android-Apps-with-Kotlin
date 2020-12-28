package com.example.loginactivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

const val USER_NAME_KEY = "USER_NAME_KEY"
const val PASSWORD_KEY = "PASSWORD_KEY"

const val IS_LOGGED_IN = "IS_LOGGED_IN"
const val LOGGED_IN_USERNAME = "LOGGED_IN_USERNAME"

class MainActivity : AppCompatActivity() {

    private var isLoggedIn = false
    private var loggedInUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit_button.setOnClickListener {

            val userName = user_name.text.toString().trim()
            val passWord = password.text.toString().trim()

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
                    startActivityForResult(welcomeIntent, LOGIN_REQUEST_CODE)
                }

            } else {
                val toast = Toast.makeText(
                    this,
                    getString(R.string.login_form_entry_error),
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean(IS_LOGGED_IN, isLoggedIn)
        outState.putString(LOGGED_IN_USERNAME, loggedInUser)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        isLoggedIn = savedInstanceState.getBoolean(IS_LOGGED_IN, false)
        loggedInUser = savedInstanceState.getString(LOGGED_IN_USERNAME, "")

        if (isLoggedIn && loggedInUser.isNotBlank()) {
            setLoggedIn(loggedInUser)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val userNameForm = data?.getStringExtra(USER_NAME_KEY) ?: ""

            setLoggedIn(userNameForm)
            isLoggedIn = true

        } else if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_CANCELED){
            val toast = Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }

    }

    private fun setLoggedIn(userName: String) {
        loggedInUser = userName
        val welcomeMessage = getString(R.string.welcome_text, userName)
        user_name.isVisible = false
        password.isVisible = false
        submit_button.isVisible = false
        header.text = welcomeMessage
    }


    private fun hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}
