package com.android.testable.myapplication

import android.app.Application

open class MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .repositoryModule(createRepositoryModule())
            .build()
    }

    open fun createRepositoryModule(): RepositoryModule {
        return RepositoryModule()
    }
}