package com.example.saveandrestore

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

private const val DISCOUNT_CONFIRMATION_MESSAGE = "DISCOUNT_CONFIRMATION_MESSAGE"
private const val DISCOUNT_CODE = "DISCOUNT_CODE"

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var generatedDiscountCode: String? = null
    private var discountConfirmationMessage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")

        discount_button.setOnClickListener {

            val firstName = (first_name as EditText).text.toString()
            val lastName = (last_name as EditText).text.toString()

            val fullName = firstName.plus(" ").plus(lastName)
            discountConfirmationMessage = getString(R.string.discount_code_confirmation, fullName)
            discount_code_confirmation.text = discountConfirmationMessage

            generatedDiscountCode = UUID.randomUUID().toString().take(8).toUpperCase()
            discount_code.text = generatedDiscountCode

            hideKeyboard()
            clearInputFields()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")

        //Get the discount code or an empty string if it hasn't been set
        generatedDiscountCode = savedInstanceState.getString(DISCOUNT_CODE,"")
        //Get the discount confirmation message or an empty string if it hasn't been set
        discountConfirmationMessage = savedInstanceState.getString(DISCOUNT_CONFIRMATION_MESSAGE,"")

        setDiscountCode(generatedDiscountCode)
        setDiscountConfirmationMessage(discountConfirmationMessage)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")

        outState.putString(DISCOUNT_CODE, generatedDiscountCode)
        outState.putString(DISCOUNT_CONFIRMATION_MESSAGE, discountConfirmationMessage)
    }

    private fun clearInputFields() {
        first_name.getText().clear()
        last_name.getText().clear()
        email.getText().clear()
    }

    private fun setDiscountConfirmationMessage(confirmationMessage: String?) {
        discount_code_confirmation.text = confirmationMessage
    }

    private fun setDiscountCode(discountCode: String?) {
        discount_code.text = discountCode
    }

    private fun hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}