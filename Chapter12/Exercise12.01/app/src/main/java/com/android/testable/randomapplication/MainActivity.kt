package com.android.testable.randomapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainContainer = MainContainer((application as RandomApplication).applicationContainer.numberRepository)
        val viewModel =  ViewModelProvider(this, mainContainer.getMainViewModelFactory()).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(this, Observer {
            activity_main_text_view.text = it.toString()
        }
        )
        activity_main_button.setOnClickListener {
            viewModel.generateNextNumber()
        }
    }
}
