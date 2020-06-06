package com.android.testable.myapplication

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun createActivitySubcomponent(): ActivitySubcomponent
}