package com.android.testable.randomapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainViewModelFactory(numberRepository: NumberRepository): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(numberRepository) as T
            }
        }
    }
}