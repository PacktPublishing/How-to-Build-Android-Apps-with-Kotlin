package com.android.testable.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var randomizer: Randomizer
    @Inject
    lateinit var numberReversal: NumberReversal

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity_main_button.setOnClickListener {
            activity_main_text_view.text =
                numberReversal.reverseNumber(randomizer.generateNumber()).toString()
        }
    }
}