package com.example.loginactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

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

    private val submitButton: Button
        get() = findViewById(R.id.submit_button)

    private val userName: EditText
        get() = findViewById(R.id.user_name)

    private val password: EditText
        get() = findViewById(R.id.password)

    private val header: TextView
        get() = findViewById(R.id.header)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton.setOnClickListener {

            val userNameForm = userName.text.toString().trim()
            val passwordForm = password.text.toString().trim()

            hideKeyboard()

            if (userNameForm.isNotEmpty() && passwordForm.isNotEmpty()) {

                //Set the name of the activity to launch
                Intent(this, MainActivity::class.java).also { loginIntent ->
                    //Add the data
                    loginIntent.putExtra(USER_NAME_KEY, userNameForm)
                    loginIntent.putExtra(PASSWORD_KEY, passwordForm)
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

    private fun setLoggedIn(loggedInUserName: String) {
        loggedInUser = loggedInUserName
        val welcomeMessage = getString(R.string.welcome_text, loggedInUserName)
        userName.isVisible = false
        password.isVisible = false
        submitButton.isVisible = false
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
