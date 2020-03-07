package com.example.catagenttracker

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RouteTrackingService : Service() {
    override fun onBind(intent: Intent): IBinder? = null
}
