package com.example.mywatterttracker

import android.app.Service
import android.content.Intent
import android.os.IBinder

class WaterTrackingService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null
}

