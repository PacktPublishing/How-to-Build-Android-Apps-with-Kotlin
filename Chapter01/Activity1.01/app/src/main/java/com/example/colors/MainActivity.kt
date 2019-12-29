package com.example.colors

import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        color_creator_button.setOnClickListener {

            val redChannelText = (red_channel as EditText).text.toString()
            val greenChannelText = (green_channel as EditText).text.toString()
            val blueChannelText = (blue_channel as EditText).text.toString()

            val colorToDisplay =
                redChannelText.plus(greenChannelText).plus(blueChannelText)

            val colorAsInt = Color.parseColor("#".plus(colorToDisplay))
            color_creator_display.setBackgroundColor(colorAsInt)

        }
    }
}
