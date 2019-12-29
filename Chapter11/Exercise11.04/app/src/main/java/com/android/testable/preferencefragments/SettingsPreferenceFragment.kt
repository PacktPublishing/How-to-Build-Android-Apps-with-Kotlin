package com.android.testable.preferencefragments

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsPreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
        val ipAddressPref = findPreference<EditTextPreference>(getString(R.string.key_ip_address))
        ipAddressPref?.setOnPreferenceChangeListener { preference, newValue ->
            preference.summary = newValue.toString()
            true
        }
        val domainPref = findPreference<EditTextPreference>(getString(R.string.key_domain))
        domainPref?.setOnPreferenceChangeListener { preference, newValue ->
            preference.summary = newValue.toString()
            true
        }

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        ipAddressPref?.summary = sharedPrefs.getString(getString(R.string.key_ip_address), "")
        domainPref?.summary = sharedPrefs.getString(getString(R.string.key_domain), "")
    }
}