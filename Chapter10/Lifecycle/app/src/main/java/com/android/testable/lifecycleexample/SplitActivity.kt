package com.android.testable.lifecycleexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SplitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(ToastyLifecycleObserver {
            Toast.makeText(this, "Started", Toast.LENGTH_LONG).show()
        })
    }


}
