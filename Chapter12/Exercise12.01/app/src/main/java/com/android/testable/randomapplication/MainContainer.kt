package com.android.testable.randomapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainContainer(private val numberRepository: NumberRepository) {

    fun getMainViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(numberRepository) as T
            }
        }
    }
}