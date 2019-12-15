package com.android.testable.files

import android.content.res.AssetManager
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.InputStream

@RunWith(MockitoJUnitRunner::class)
class AssetFileManagerTest {

    @InjectMocks
    lateinit var assetFileManager: AssetFileManager
    @Mock
    lateinit var assetManager: AssetManager


    @Test
    fun getMyAppFileInputStream() {
        val expected = Mockito.mock(InputStream::class.java)
        Mockito.`when`(assetManager.open("my-app-file.txt")).thenReturn(expected)
        val result = assetFileManager.getMyAppFileInputStream()
        Assert.assertEquals(expected, result)
    }
}