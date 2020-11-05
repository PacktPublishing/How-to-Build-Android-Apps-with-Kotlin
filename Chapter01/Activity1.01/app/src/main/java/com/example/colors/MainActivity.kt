package com.example.colors

import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        color_creator_button.setOnClickListener {
            var redChannelText = red_channel.text.toString()
            var greenChannelText = green_channel.text.toString()
            var blueChannelText = blue_channel.text.toString()

            // Check that all fields are filled in and show error message if not.
            if (redChannelText.isEmpty() or greenChannelText.isEmpty() or blueChannelText.isEmpty()) {
                Toast.makeText(this, "All Values are required", Toast.LENGTH_LONG).show()
            } else {
                // check that 2 hexadecimal characters have been entered and if not add the same hexadecimal character again.
                if (redChannelText.length == 1) redChannelText = redChannelText.plus(redChannelText)
                if (greenChannelText.length == 1) greenChannelText = greenChannelText.plus(greenChannelText)
                if (blueChannelText.length == 1) blueChannelText = blueChannelText.plus(blueChannelText)

                val colorToDisplay = redChannelText.plus(greenChannelText).plus(blueChannelText)

                val colorAsInt = Color.parseColor("#".plus(colorToDisplay))
                color_creator_display.setBackgroundColor(colorAsInt)

            }
        }
    }
}
