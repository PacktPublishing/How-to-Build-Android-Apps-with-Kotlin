package com.example.loginactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.header
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    var isLoggedIn = false
    var loggedInUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        //Get the intent which started this activity
        intent.let { loginIntent ->

            val userNameForm = loginIntent?.getStringExtra(USER_NAME_KEY) ?: ""
            val passwordForm = loginIntent?.getStringExtra(PASSWORD_KEY) ?: ""

            val loggedInCorrectly =
                hasEnteredCorrectCredentials(userNameForm.trim(), passwordForm.trim())

            if (loggedInCorrectly) {
                setLoggedIn(userNameForm)
                isLoggedIn = true
            } else {
                header.text = getString(R.string.login_error)
                back_button.isVisible = true
                back_button.setOnClickListener {
                    //Finishes this activity and so goes back to the previous activity
                    finish()
                }
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

    private fun setLoggedIn(userName: String) {
        loggedInUser = userName
        val welcomeMessage = getString(R.string.welcome_text, userName)
        back_button.isVisible = false
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
}
