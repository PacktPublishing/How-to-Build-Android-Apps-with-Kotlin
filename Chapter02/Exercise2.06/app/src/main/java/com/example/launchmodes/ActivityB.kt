package com.example.launchmodes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class ActivityB : AppCompatActivity() {

    private val TAG = "Activity B"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        Log.d(TAG, "onCreate")


        val buttonClickListener = View.OnClickListener { view ->

            when (view.id) {
                R.id.letterA -> startActivity(Intent(this, ActivityA::class.java))
                R.id.letterB -> startActivity(Intent(this, ActivityB::class.java))
                R.id.letterC -> startActivity(Intent(this, ActivityC::class.java))
                else -> {
                    Toast.makeText(
                        this,
                        getString(R.string.unexpected_button_pressed),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }

        findViewById<View>(R.id.letterA).setOnClickListener(buttonClickListener)
        findViewById<View>(R.id.letterB).setOnClickListener(buttonClickListener)
        findViewById<View>(R.id.letterC).setOnClickListener(buttonClickListener)
    }
}
