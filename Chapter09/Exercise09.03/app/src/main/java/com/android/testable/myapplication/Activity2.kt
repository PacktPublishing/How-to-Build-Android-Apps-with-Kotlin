package com.android.testable.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {

    companion object {

        private const val EXTRA_SECONDS = "extra_seconds"

        fun newIntent(context: Context, seconds: Int) =
            Intent(context, Activity2::class.java).putExtra(
                EXTRA_SECONDS, seconds
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        findViewById<TextView>(R.id.activity_2_text_view).text =
            getString(R.string.opened_after_x_seconds, intent.getIntExtra(EXTRA_SECONDS, 0))
    }
}