package com.android.testable.myapplication.repository

import androidx.lifecycle.LiveData
import com.android.testable.myapplication.api.Post

interface PostRepository {

    fun getPosts(): LiveData<List<Post>>

}