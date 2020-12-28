package com.example.loginactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
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

    private var isLoggedIn = false
    private var loggedInUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit_button.setOnClickListener {

            val userName = user_name.text.toString().trim()
            val password = password.text.toString().trim()

            hideKeyboard()

            if (userName.isNotEmpty() && password.isNotEmpty()) {

                //Set the name of the activity to launch
                Intent(this, MainActivity::class.java).also { loginIntent ->
                    //Add the data
                    loginIntent.putExtra(USER_NAME_KEY, userName)
                    loginIntent.putExtra(PASSWORD_KEY, password)
                    //Launch
                    startActivity(loginIntent)
                }

            } else {
                val toast = Toast.makeText(this, getString(R.string.login_form_entry_error), Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }

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

    private fun hasEnteredCorrectCredentials(
        userNameForm: String,
        passwordForm: String
    ): Boolean {
        return userNameForm.contentEquals(USER_NAME_CORRECT_VALUE) && passwordForm.contentEquals(
            PASSWORD_CORRECT_VALUE
        )
    }

    override fun onNewIntent(newIntent: Intent?) {
        super.onNewIntent(newIntent)

        //Set the new Intent to the one to process
        intent = newIntent

        //Get the intent which started this activity
        intent?.let { loginIntent ->

            val userNameForm = loginIntent.getStringExtra(USER_NAME_KEY) ?: ""
            val passwordForm = loginIntent.getStringExtra(PASSWORD_KEY) ?: ""

            val loggedInCorrectly =
                hasEnteredCorrectCredentials(userNameForm.trim(), passwordForm.trim())

            if (loggedInCorrectly) {
                setLoggedIn(userNameForm)
                isLoggedIn = true
            } else {
                val toast = Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_LONG)
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

        if (isLoggedIn && loggedInUser.isNotEmpty()) {
            setLoggedIn(loggedInUser)
        }
    }

    private fun hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}
