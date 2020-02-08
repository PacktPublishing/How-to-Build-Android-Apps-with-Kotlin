package com.android.testable.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_1.*

class Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)
        button.setOnClickListener {
            (application as MyApplication).synchronizer.executeAfterDelay {
                startActivity(Activity2.newIntent(this, it))
            }
        }
    }
}