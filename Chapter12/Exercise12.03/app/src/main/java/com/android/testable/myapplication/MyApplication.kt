package com.android.testable.myapplication

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.util.*

class MyApplication : Application() {

    private val appModule = module {
        single {
            Random()
        }
        single {
            Randomizer(get())
        }
    }

    private val mainActivityModule = module {
        factory {
            NumberReversal()
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(appModule, mainActivityModule))
        }
    }
}