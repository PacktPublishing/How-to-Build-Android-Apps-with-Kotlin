package com.example.mywatterttracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.mywatterttracker.WaterTrackingService.Companion.EXTRA_INTAKE_AMOUNT_MILLILITERS
import kotlinx.android.synthetic.main.activity_main.main_water_button as waterButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launchTrackingService()

        waterButton.setOnClickListener {
            launchTrackingService(250f)
        }
    }

    private fun launchTrackingService(intakeAmount: Float = 0f) {
        val serviceIntent = Intent(this, WaterTrackingService::class.java).apply {
            putExtra(EXTRA_INTAKE_AMOUNT_MILLILITERS, intakeAmount)
        }
        ContextCompat.startForegroundService(this, serviceIntent)
    }
}
