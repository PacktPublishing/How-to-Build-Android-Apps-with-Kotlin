package com.android.testable.myapplication

import dagger.Subcomponent

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivitySubcomponent {

    fun inject(mainActivity: MainActivity)
}