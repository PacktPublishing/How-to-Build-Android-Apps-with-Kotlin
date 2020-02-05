package com.android.testable.myapplication

import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideNumberReversal(): NumberReversal = NumberReversal()
}