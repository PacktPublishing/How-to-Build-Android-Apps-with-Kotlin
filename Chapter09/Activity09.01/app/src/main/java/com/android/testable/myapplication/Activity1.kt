package com.android.testable.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_1.*

class Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)
        activity_1_button.setOnClickListener {
            startActivity(Activity2.newIntent(this, activity_1_edit_text.text.toString().toIntOrNull() ?: 0))
        }
    }
}