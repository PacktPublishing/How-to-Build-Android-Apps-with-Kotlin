package com.android.testable.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textFormatter: TextFormatter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textFormatter = TextFormatter(NumberAdder(), applicationContext)
        findViewById<Button>(R.id.button).setOnClickListener {
            textFormatter.getSumResult(findViewById<EditText>(R.id.edit_text).text.toString().toIntOrNull() ?: 0) {
                findViewById<TextView>(R.id.text_view).text = it
            }
        }
    }
}