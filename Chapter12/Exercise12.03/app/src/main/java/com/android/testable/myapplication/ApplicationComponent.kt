package com.android.testable.myapplication

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, AndroidSupportInjectionModule::class, ActivityProviderModule::class])
interface ApplicationComponent {

    fun inject(myApplication: MyApplication)
}