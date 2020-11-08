package com.android.testable.sharedpreferences

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferenceWrapper = (application as PreferenceApplication).preferenceWrapper
        val preferenceViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PreferenceViewModel(preferenceWrapper) as T
            }

        }).get(PreferenceViewModel::class.java)

        preferenceViewModel.getText().observe(this, Observer {
            activity_main_text_view.text = it
        })

        activity_main_button.setOnClickListener {
            preferenceViewModel.saveText(activity_main_edit_text.text.toString())
        }
    }
}
