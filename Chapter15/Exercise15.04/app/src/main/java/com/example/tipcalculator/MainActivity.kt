package com.example.tipcalculator

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amountText: EditText = findViewById(R.id.amount_text)
        val percentText: EditText = findViewById(R.id.percent_text)
        val computeButton: Button = findViewById(R.id.compute_button)
        computeButton.setOnClickListener {
            val amount =
                    if (amountText.text.toString().isNotBlank()) amountText.text.toString() else "0"
            val percent =
                    if (percentText.text.toString().isNotBlank()) percentText.text.toString() else "0"
            val intent = Intent(this, OutputActivity::class.java).apply {
                putExtra("amount", amount)
                putExtra("percent", percent)
            }

            val image: ImageView = findViewById(R.id.image)
            startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this, image, "transition_name")
                    .toBundle()
            );
        }
    }

}
