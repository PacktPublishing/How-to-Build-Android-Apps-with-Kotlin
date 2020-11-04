package com.example.saveandrestore

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

private const val DISCOUNT_CONFIRMATION_MESSAGE = "DISCOUNT_CONFIRMATION_MESSAGE"
private const val DISCOUNT_CODE = "DISCOUNT_CODE"

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")

        // here we handle the Button onClick event
        discount_button.setOnClickListener {

            val firstName = first_name.text.toString()
            val lastName = last_name.text.toString()
            val email = email.text.toString()

            if (firstName.isBlank() || lastName.isBlank() || email.isEmpty()){
                Toast.makeText(this, getString(R.string.add_text_validation), Toast.LENGTH_LONG).show()
            }
            else {
                val fullName = firstName.plus(" ").plus(lastName)

                discount_code_confirmation.text =
                    getString(R.string.discount_code_confirmation, fullName)
                // Generates discount code
                discount_code.text = UUID.randomUUID().toString().take(8).toUpperCase()

                hideKeyboard()
                clearInputFields()
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")

        //Get the discount code or an empty string if it hasn't been set
        discount_code.text = savedInstanceState.getString(DISCOUNT_CODE,"")
        //Get the discount confirmation message or an empty string if it hasn't been set
        discount_code_confirmation.text = savedInstanceState.getString(DISCOUNT_CONFIRMATION_MESSAGE,"")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")

        outState.putString(DISCOUNT_CODE, discount_code.text.toString())
        outState.putString(DISCOUNT_CONFIRMATION_MESSAGE, discount_code_confirmation.text.toString())
    }

    private fun clearInputFields() {
        first_name.text.clear()
        last_name.text.clear()
        email.text.clear()
    }

    private fun hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}
