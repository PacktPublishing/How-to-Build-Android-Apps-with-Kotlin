package com.example.mywatterttracker

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import androidx.core.app.NotificationCompat

class WaterTrackingService : Service() {
    private var fluidBalanceMilliliters = 0f
    private lateinit var notificationBuilder: NotificationCompat.Builder
    private lateinit var serviceHandler: Handler

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        const val EXTRA_INTAKE_AMOUNT_MILLILITERS = "intake"
        private const val NOTIFICATION_ID = 0x3A7A
    }
}
