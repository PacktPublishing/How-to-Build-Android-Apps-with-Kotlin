package com.example.tipcalculator

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compute_button.setOnClickListener {
            val amount = if (amount_text.text.toString().isNotBlank()) amount_text.text.toString() else "0"
            val percent = if (percent_text.text.toString().isNotBlank()) percent_text.text.toString() else "0"
            val intent = Intent(this, OutputActivity::class.java).apply {
                putExtra("amount", amount)
                putExtra("percent", percent)
            }
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, image, "transition_name").toBundle());
        }
    }

}
