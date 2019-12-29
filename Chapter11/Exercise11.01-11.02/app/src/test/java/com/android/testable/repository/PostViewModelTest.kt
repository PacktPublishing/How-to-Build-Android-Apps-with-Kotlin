package com.android.testable.repository

import androidx.lifecycle.LiveData
import com.android.testable.repository.repository.PostRepository
import com.android.testable.repository.repository.Result
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {

    @InjectMocks
    lateinit var postViewModel: PostViewModel
    @Mock
    lateinit var postRepository: PostRepository

    @Test
    fun getPosts() {
        val expected = Mockito.mock(LiveData::class.java)
        Mockito.`when`(postRepository.getPosts())
            .thenReturn(expected as LiveData<Result>?)

        val result = postViewModel.getPosts()

        assertEquals(expected, result)
    }
}