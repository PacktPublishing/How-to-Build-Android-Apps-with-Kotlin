package com.example.passwordgenerator

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generate_button.setOnClickListener {
            if (length_text.text.toString().isNullOrBlank() || length_text.text.toString()
                    .toInt() < 6
            ) {
                Snackbar.make(it, "Length must be six and above", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, OutputActivity::class.java).apply {
                putExtra("length", length_text.text.toString())
                putExtra("uppercase", uppercase_check.isChecked)
                putExtra("numbers", number_check.isChecked)
                putExtra("special", special_check.isChecked)
            }
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }
}