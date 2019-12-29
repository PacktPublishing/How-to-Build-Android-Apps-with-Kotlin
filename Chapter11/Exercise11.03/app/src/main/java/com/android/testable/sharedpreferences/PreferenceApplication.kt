package com.android.testable.sharedpreferences

import android.app.Application
import android.content.Context

class PreferenceApplication : Application() {

    lateinit var preferenceWrapper: PreferenceWrapper

    override fun onCreate() {
        super.onCreate()
        preferenceWrapper = PreferenceWrapper(getSharedPreferences("prefs", Context.MODE_PRIVATE))
    }
}