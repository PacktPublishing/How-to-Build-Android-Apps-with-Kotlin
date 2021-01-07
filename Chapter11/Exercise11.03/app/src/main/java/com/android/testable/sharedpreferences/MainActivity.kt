package com.android.testable.sharedpreferences

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

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
            findViewById<TextView>(R.id.activity_main_text_view).text = it
        })

        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            preferenceViewModel.saveText(findViewById<EditText>(R.id.activity_main_edit_text).text.toString())
        }
    }
}
