package com.android.testable.randomapplication

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun createMainSubcomponent(): MainSubcomponent
}