package com.android.testable.randomapplication

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import org.koin.android.scope.ScopeActivity

class MainActivity : ScopeActivity() {

    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.getLiveData().observe(this, Observer {
            findViewById<TextView>(R.id.activity_main_text_view).text = it.toString()
        }
        )
        findViewById<TextView>(R.id.activity_main_button).setOnClickListener {
            mainViewModel.generateNextNumber()
        }
    }
}
