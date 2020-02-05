package com.android.testable.myapplication

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivitySubcomponent {

    fun inject(mainActivity: MainActivity)
}