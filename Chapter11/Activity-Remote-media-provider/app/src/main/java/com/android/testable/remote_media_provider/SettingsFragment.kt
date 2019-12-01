package com.android.testable.remote_media_provider

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
        val resultsPreference =
            findPreference<EditTextPreference>(getString(R.string.preference_key_nr_results))
        val preferencesWrapper =
            (requireActivity().application as RemoteProviderApplication).preferencesWrapper
        resultsPreference?.summary = preferencesWrapper.getNumberOfResults().toString()

        resultsPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                resultsPreference?.summary = newValue?.toString()
                true
            }
    }
}