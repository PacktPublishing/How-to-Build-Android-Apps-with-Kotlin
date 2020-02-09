package com.android.testable.myapplication

import com.android.testable.myapplication.api.PostService
import com.android.testable.myapplication.repository.PostRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestRepositoryModule : RepositoryModule() {

    @Singleton
    @Provides
    override fun providePostRepository(postService: PostService): PostRepository {
        return DummyRepository()
    }
}