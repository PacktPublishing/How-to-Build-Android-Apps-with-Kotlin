package com.android.testable.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.testable.repository.repository.PostRepository
import com.android.testable.repository.repository.Result

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    fun getPosts(): LiveData<Result> = postRepository.getPosts()
}