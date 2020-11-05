package com.android.testable.randomapplication

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, AndroidSupportInjectionModule::class, MainProviderModule::class])
interface ApplicationComponent {

    fun inject(randomApplication: RandomApplication)
}