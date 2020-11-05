package com.android.testable.myapplication

import com.android.testable.myapplication.api.PostService
import com.android.testable.myapplication.repository.PostRepository
import com.android.testable.myapplication.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @Singleton
    @Provides
    open fun providePostRepository(postService: PostService): PostRepository {
        return PostRepositoryImpl(postService)
    }
}