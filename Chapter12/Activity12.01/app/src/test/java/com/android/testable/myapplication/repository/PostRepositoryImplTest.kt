package com.android.testable.myapplication.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.testable.myapplication.api.Post
import com.android.testable.myapplication.api.PostService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class PostRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @InjectMocks
    lateinit var postRepository: PostRepositoryImpl
    @Mock
    lateinit var postService: PostService
    @Mock
    lateinit var call: Call<List<Post>>

    @Before
    fun setUp() {
        Mockito.`when`(postService.getPosts()).thenReturn(call)
    }

    @Test
    fun getPosts_success() {
        val postList = listOf(
            Post(1, 1, "title1", "body1"),
            Post(2, 2, "title2", "body2")
        )
        Mockito.`when`(call.enqueue(Mockito.any())).thenAnswer {
            (it.arguments[0] as Callback<List<Post>>).onResponse(call, Response.success(postList))
        }

        val result = postRepository.getPosts()

        assertEquals(postList, result.value)
    }
}