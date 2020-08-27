package com.example.architecturedemo

import android.os.CountDownTimer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.architecturedemo.StopWatchListener

internal class StopWatch(lifecycle: Lifecycle, stopWatchListener: StopWatchListener) :
    LifecycleObserver {

    var stopWatchListener: StopWatchListener
    private lateinit var timer: CountDownTimer
    private var timerValue: Long = 0

    init {
        lifecycle.addObserver(this)
        this.stopWatchListener = stopWatchListener
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun start() {
        timer =
            object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timerValue++
                    stopWatchListener.onTimerUpdated(timerValue.toString())
                }

                override fun onFinish() {
                }
            }.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stop() {
        timer.cancel()
    }
}