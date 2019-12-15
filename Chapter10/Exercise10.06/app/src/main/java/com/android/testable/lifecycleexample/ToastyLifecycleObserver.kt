package com.android.testable.lifecycleexample

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class ToastyLifecycleObserver(private val onStarted: () -> Unit) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStarted() {
        onStarted.invoke()
    }
}