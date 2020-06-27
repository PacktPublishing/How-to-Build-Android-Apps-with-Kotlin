package com.example.architecturedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturedemo.R
import com.example.architecturedemo.StopWatch
import com.example.architecturedemo.StopWatchListener
import kotlinx.android.synthetic.main.activity_main.*

class Exercise01 : AppCompatActivity(), StopWatchListener {

    private lateinit var stopWatchTimer: StopWatch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stopWatchTimer = StopWatch(this.lifecycle, this)
    }

    override fun onTimerUpdated(timer: String) {
        runOnUiThread {
            timerText.text = timer
        }
    }
}
