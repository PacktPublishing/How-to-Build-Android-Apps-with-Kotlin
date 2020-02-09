package com.android.testable.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.testable.myapplication.repository.PostRepository
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideViewModelFactory(postRepository: PostRepository): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PostViewModel(postRepository) as T
            }
        }
    }
}