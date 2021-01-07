package com.android.testable.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)
        findViewById<Button>(R.id.activity_1_button).setOnClickListener {
            startActivity(
                Activity2.newIntent(
                    this,
                    findViewById<EditText>(R.id.activity_1_edit_text).text.toString().toIntOrNull()
                        ?: 0
                )
            )
        }
    }
}