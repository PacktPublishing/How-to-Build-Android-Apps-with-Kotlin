package com.android.testable.repository.repository

import com.android.testable.repository.api.Post
import com.android.testable.repository.db.PostEntity

class PostMapper {

    fun serviceToEntity(post: Post): PostEntity {
        return PostEntity(post.id, post.userId, post.title, post.body)
    }

    fun serviceToUi(post: Post): UiPost {
        return UiPost(post.title, post.body)
    }
}