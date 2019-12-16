package com.android.testable.camera

import android.content.Context
import android.net.Uri
import android.os.Environment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@RunWith(MockitoJUnitRunner::class)
class ProviderFileManagerTest {

    @InjectMocks
    lateinit var providerFileManager: ProviderFileManager
    @Mock
    lateinit var context: Context
    @Mock
    lateinit var fileHelper: FileHelper


    @Test
    fun generatePhotoUri() {
        val root = File("root")
        Mockito.`when`(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)).thenReturn(root)
        val time = 10L
        val uri = Mockito.mock(Uri::class.java)
        Mockito.`when`(fileHelper.getUriFromFile(File(root, "img_$time.jpg"))).thenReturn(uri)
        val result = providerFileManager.generatePhotoUri(time)
        assertEquals(FileInfo(uri, File(root, "img_$time.jpg")), result)
    }

    @Test
    fun generateVideoUri() {
        val root = File("root")
        Mockito.`when`(context.getExternalFilesDir(Environment.DIRECTORY_MOVIES)).thenReturn(root)
        val time = 10L
        val uri = Mockito.mock(Uri::class.java)
        Mockito.`when`(fileHelper.getUriFromFile(File(root, "video_$time.mp4"))).thenReturn(uri)
        val result = providerFileManager.generateVideoUri(time)
        assertEquals(FileInfo(uri, File(root, "video_$time.mp4")), result)
    }
}