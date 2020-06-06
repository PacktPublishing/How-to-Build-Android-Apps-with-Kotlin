package com.android.testable.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val numberReversal: NumberReversal by inject()
    private val randomizer: Randomizer by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity_main_button.setOnClickListener {
            activity_main_text_view.text = numberReversal.reverseNumber(randomizer.generateNumber()).toString()
        }
    }
}