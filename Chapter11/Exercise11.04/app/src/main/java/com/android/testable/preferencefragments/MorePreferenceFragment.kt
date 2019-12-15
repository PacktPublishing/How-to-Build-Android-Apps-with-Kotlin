package com.android.testable.preferencefragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class MorePreferenceFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_more, rootKey)
    }
}