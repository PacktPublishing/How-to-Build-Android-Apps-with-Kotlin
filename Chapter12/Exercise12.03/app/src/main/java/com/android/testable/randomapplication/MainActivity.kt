package com.android.testable.randomapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(this, Observer {
            activity_main_text_view.text = it.toString()
        }
        )
        activity_main_button.setOnClickListener {
            viewModel.generateNextNumber()
        }
    }
}
