package com.android.testable.repository.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.testable.repository.api.Post
import com.android.testable.repository.api.PostService
import com.android.testable.repository.db.PostDao
import com.android.testable.repository.db.PostEntity
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class PostRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @InjectMocks
    lateinit var postRepository: PostRepositoryImpl
    @Mock
    lateinit var postService: PostService
    @Mock
    lateinit var postDao: PostDao
    @Mock
    lateinit var postMapper: PostMapper
    @Mock
    lateinit var executor: Executor
    @Mock
    lateinit var call: Call<List<Post>>

    @Before
    fun setUp() {
        Mockito.`when`(executor.execute(Mockito.any())).thenAnswer {
            (it.arguments[0] as Runnable).run()
        }
        Mockito.`when`(postService.getPosts()).thenReturn(call)
    }

    @Test
    fun getPosts_success() {
        val postList = listOf(
            Post(1, 1, "title1", "body1"),
            Post(2, 2, "title2", "body2")
        )
        val postEntityList = listOf(
            PostEntity(1, 1, "title1", "body1"),
            PostEntity(2, 2, "title2", "body2")
        )
        val postUiList = listOf(
            UiPost("title1", "body1"),
            UiPost("title2", "body2")
        )
        for (i in postList.indices) {
            Mockito.`when`(postMapper.serviceToEntity(postList[i])).thenReturn(postEntityList[i])
            Mockito.`when`(postMapper.serviceToUi(postList[i])).thenReturn(postUiList[i])
        }
        Mockito.`when`(call.enqueue(Mockito.any())).thenAnswer {
            (it.arguments[0] as Callback<List<Post>>).onResponse(call, Response.success(postList))
        }

        val result = postRepository.getPosts()

        assertEquals(postUiList, (result.value as Result.Success).uiPosts)
        Mockito.verify(postDao).insertPosts(postEntityList)

    }

    @Test
    fun getPosts_httpError() {
        Mockito.`when`(call.enqueue(Mockito.any())).thenAnswer {
            (it.arguments[0] as Callback<List<Post>>).onResponse(
                call,
                Response.error(400, Mockito.mock(ResponseBody::class.java))
            )
        }

        val result = postRepository.getPosts()

        assertTrue(result.value is Result.Error)

    }

    @Test
    fun getPosts_error() {
        val error = RuntimeException("Test")
        Mockito.`when`(call.enqueue(Mockito.any())).thenAnswer {
            (it.arguments[0] as Callback<List<Post>>).onFailure(call, error)
        }

        val result = postRepository.getPosts()

        assertEquals(error, (result.value as Result.Error).throwable)
    }
}