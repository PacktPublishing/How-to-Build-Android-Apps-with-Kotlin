package com.android.testable.repository.repository

import androidx.lifecycle.LiveData

interface PostRepository {

    fun getPosts(): LiveData<Result>

}