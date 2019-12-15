package com.android.testable.repository.repository

sealed class Result {
    object Loading : Result()
    class Success(val uiPosts: List<UiPost>) : Result()
    class Error(val throwable: Throwable) : Result()
}