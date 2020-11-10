package com.android.testable.randomapplication

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.ScopeActivity

class MainActivity : ScopeActivity() {

    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.getLiveData().observe(this, Observer {
            activity_main_text_view.text = it.toString()
        }
        )
        activity_main_button.setOnClickListener {
            mainViewModel.generateNextNumber()
        }
    }
}
