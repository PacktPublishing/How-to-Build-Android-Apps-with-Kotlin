package com.android.testable.myapplication

import dagger.Module
import dagger.Provides
import java.util.*

@Module
class ApplicationModule {

    @Provides
    fun provideRandom(): Random = Random()

    @Provides
    fun provideRandomizer(random: Random): Randomizer = Randomizer(random)
}