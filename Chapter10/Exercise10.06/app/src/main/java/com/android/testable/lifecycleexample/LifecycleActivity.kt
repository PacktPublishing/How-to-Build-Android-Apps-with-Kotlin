package com.android.testable.lifecycleexample

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class LifecycleActivity : Activity(), LifecycleOwner {

    private val lifecycleRegistry: LifecycleRegistry by lazy {
        LifecycleRegistry(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleRegistry.currentState = Lifecycle.State.CREATED

        lifecycleRegistry.addObserver(ToastyLifecycleObserver {
            Toast.makeText(applicationContext, "Started", Toast.LENGTH_LONG).show()
        })
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    override fun onStop() {
        super.onStop()
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }
}
