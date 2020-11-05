package com.android.testable.remote_media_provider.storage.preference

import android.content.Context
import android.content.SharedPreferences
import com.android.testable.remote_media_provider.R

const val DEFAULT_NO_OF_RESULTS = 10

class DownloadPreferencesWrapper(
    private val context: Context,
    private val sharedPreferences: SharedPreferences
) {

    fun getNumberOfResults(): Int {
        return sharedPreferences.getString(
            context.getString(R.string.preference_key_nr_results),
            DEFAULT_NO_OF_RESULTS.toString()
        ).orEmpty().toIntOrNull() ?: DEFAULT_NO_OF_RESULTS
    }
}