package com.android.testable.myapplication

import com.android.testable.myapplication.api.PostService
import com.android.testable.myapplication.repository.PostRepository

class TestApplication : MyApplication() {

    override fun providePostRepository(postService: PostService): PostRepository {
        return DummyRepository()
    }
}