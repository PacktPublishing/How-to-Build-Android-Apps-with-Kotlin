package com.android.testable.sharedpreferences

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

const val KEY_TEXT = "keyText"

class PreferenceWrapper(private val sharedPreferences: SharedPreferences) {

    private val textLiveData = MutableLiveData<String>()

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener { _, key ->
            when (key) {
                KEY_TEXT -> {
                    textLiveData.postValue(sharedPreferences.getString(KEY_TEXT, ""))
                }
            }
        }
    }

    fun saveText(text: String) {
        sharedPreferences.edit()
            .putString(KEY_TEXT, text)
            .apply()
    }

    fun getText(): LiveData<String> {
        textLiveData.postValue(sharedPreferences.getString(KEY_TEXT, ""))
        return textLiveData
    }
}