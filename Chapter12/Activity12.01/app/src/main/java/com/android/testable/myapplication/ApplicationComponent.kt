package com.android.testable.myapplication

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface ApplicationComponent {



    fun createActivitySubcomponent(): MainActivitySubcomponent
}
