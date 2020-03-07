package com.example.catagenttracker

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RouteTrackingService : Service() {
    override fun onBind(intent: Intent): IBinder? = null

    private fun getPendingIntent() =
        PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), 0)

    companion object {
        const val NOTIFICATION_ID = 0xCA7
        const val EXTRA_SECRET_CAT_AGENT_ID = "scaId"

        private val mutableTrackingCompletion = MutableLiveData<String>()
        val trackingCompletion: LiveData<String> = mutableTrackingCompletion
    }
}
