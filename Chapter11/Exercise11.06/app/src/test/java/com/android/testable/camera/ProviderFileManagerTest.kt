package com.android.testable.camera

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.concurrent.Executor

@RunWith(MockitoJUnitRunner::class)
class ProviderFileManagerTest {

    @InjectMocks
    lateinit var providerFileManager: ProviderFileManager

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var fileHelper: FileHelper

    @Mock
    lateinit var contentResolver: ContentResolver

    @Mock
    lateinit var executor: Executor

    @Mock
    lateinit var mediaContentHelper: MediaContentHelper

    @Before
    fun setup() {
        Mockito.doAnswer {
            (it.arguments[0] as Runnable).run()
        }.`when`(executor).execute(Mockito.any())
    }


    @Test
    fun generatePhotoUri() {
        val root = File("root")
        val picturesFolder = "picturesFolder"
        Mockito.`when`(fileHelper.getPicturesFolder()).thenReturn(picturesFolder)
        Mockito.`when`(context.getExternalFilesDir(picturesFolder)).thenReturn(root)
        val time = 10L
        val uri = Mockito.mock(Uri::class.java)
        Mockito.`when`(fileHelper.getUriFromFile(File(root, "img_$time.jpg"))).thenReturn(uri)
        val result = providerFileManager.generatePhotoUri(time)
        assertEquals(
            FileInfo(
                uri,
                File(root, "img_$time.jpg"),
                "img_$time.jpg",
                picturesFolder,
                "image/jpeg"
            ), result
        )
    }

    @Test
    fun generateVideoUri() {
        val root = File("root")
        val videosFolder = "videosFolder"
        Mockito.`when`(fileHelper.getVideosFolder()).thenReturn(videosFolder)
        Mockito.`when`(context.getExternalFilesDir(videosFolder)).thenReturn(root)
        val time = 10L
        val uri = Mockito.mock(Uri::class.java)
        Mockito.`when`(fileHelper.getUriFromFile(File(root, "video_$time.mp4"))).thenReturn(uri)
        val result = providerFileManager.generateVideoUri(time)
        assertEquals(
            FileInfo(
                uri,
                File(root, "video_$time.mp4"),
                "video_$time.mp4",
                videosFolder,
                "video/mp4"
            ), result
        )
    }

    @Test
    fun insertImageToStore() {
        val initialUri = Mockito.mock(Uri::class.java)
        val fileInfo = Mockito.mock(FileInfo::class.java)
        val imageContentUri = Mockito.mock(Uri::class.java)
        val contentValues = Mockito.mock(ContentValues::class.java)
        val insertedUri = Mockito.mock(Uri::class.java)
        Mockito.`when`(fileInfo.uri).thenReturn(initialUri)
        Mockito.`when`(mediaContentHelper.getImageContentUri()).thenReturn(imageContentUri)
        Mockito.`when`(mediaContentHelper.generateImageContentValues(fileInfo))
            .thenReturn(contentValues)
        Mockito.`when`(contentResolver.insert(imageContentUri, contentValues))
            .thenReturn(insertedUri)

        val byteArray = ByteArray(5)
        byteArray[0] = 0
        byteArray[1] = 1
        byteArray[2] = 2
        byteArray[3] = 3
        byteArray[4] = 4
        Mockito.`when`(contentResolver.openInputStream(initialUri))
            .thenReturn(ByteArrayInputStream(byteArray))
        val outputStream = ByteArrayOutputStream()
        Mockito.`when`(contentResolver.openOutputStream(insertedUri)).thenReturn(outputStream)

        providerFileManager.insertImageToStore(fileInfo)

        assertArrayEquals(byteArray, outputStream.toByteArray())
    }

    @Test
    fun insertVideoToStore() {
        val initialUri = Mockito.mock(Uri::class.java)
        val fileInfo = Mockito.mock(FileInfo::class.java)
        val imageContentUri = Mockito.mock(Uri::class.java)
        val contentValues = Mockito.mock(ContentValues::class.java)
        val insertedUri = Mockito.mock(Uri::class.java)
        Mockito.`when`(fileInfo.uri).thenReturn(initialUri)
        Mockito.`when`(mediaContentHelper.getVideoContentUri()).thenReturn(imageContentUri)
        Mockito.`when`(mediaContentHelper.generateVideoContentValues(fileInfo))
            .thenReturn(contentValues)
        Mockito.`when`(contentResolver.insert(imageContentUri, contentValues))
            .thenReturn(insertedUri)

        val byteArray = ByteArray(5)
        byteArray[0] = 0
        byteArray[1] = 1
        byteArray[2] = 2
        byteArray[3] = 3
        byteArray[4] = 4
        Mockito.`when`(contentResolver.openInputStream(initialUri))
            .thenReturn(ByteArrayInputStream(byteArray))
        val outputStream = ByteArrayOutputStream()
        Mockito.`when`(contentResolver.openOutputStream(insertedUri)).thenReturn(outputStream)

        providerFileManager.insertVideoToStore(fileInfo)

        assertArrayEquals(byteArray, outputStream.toByteArray())
    }

}