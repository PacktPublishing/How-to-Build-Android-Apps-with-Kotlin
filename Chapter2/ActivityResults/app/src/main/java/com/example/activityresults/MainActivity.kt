package com.example.activityresults

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*


const val PICK_RAINBOW_COLOR_INTENT = 1  // The request code
const val RAINBOW_COLOR_NAME = "RAINBOW_COLOR_NAME" // Key to return rainbow color name in intent
const val RAINBOW_COLOR = "RAINBOW_COLOR" // Key to return rainbow color in intent
const val DEFAULT_COLOR = "#FFFFFF" // White

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit_button.setOnClickListener {

            //Set the name of the activity to launch passing in request code
            Intent(this, RainbowColorPickerActivity::class.java).also { rainbowColorPickerIntent ->
                startActivityForResult(rainbowColorPickerIntent, PICK_RAINBOW_COLOR_INTENT)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == PICK_RAINBOW_COLOR_INTENT && resultCode == Activity.RESULT_OK) {

            val backgroundColor = data?.getIntExtra(RAINBOW_COLOR, Color.parseColor("White")) ?: Color.parseColor("White")
            val colorName = data?.getStringExtra(RAINBOW_COLOR_NAME) ?: ""
            val colorMessage = getString(R.string.color_chosen_message, colorName)

            rainbow_color.setBackgroundColor(ContextCompat.getColor(this, backgroundColor))
            rainbow_color.text = colorMessage
            rainbow_color.isVisible = true
        }
    }

}
