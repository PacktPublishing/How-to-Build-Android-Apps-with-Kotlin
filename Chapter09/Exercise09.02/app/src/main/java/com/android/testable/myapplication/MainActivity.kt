package com.android.testable.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var textFormatter: TextFormatter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textFormatter = TextFormatter(NumberAdder(), applicationContext)
        button.setOnClickListener {
            textFormatter.getSumResult(edit_text.text.toString().toIntOrNull() ?: 0) {
                text_view.text = it
            }
        }
    }
}