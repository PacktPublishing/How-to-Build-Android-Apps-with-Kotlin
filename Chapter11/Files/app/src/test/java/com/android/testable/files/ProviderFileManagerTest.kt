package com.android.testable.files

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.Executor


@RunWith(MockitoJUnitRunner::class)
class ProviderFileManagerTest {

    @get:Rule
    var folder = TemporaryFolder()

    @InjectMocks
    lateinit var providerFileManager: ProviderFileManager
    @Mock
    lateinit var context: Context
    @Mock
    lateinit var fileToUriMapper: FileToUriMapper
    @Mock
    lateinit var executor: Executor
    @Mock
    lateinit var uri: Uri
    @Mock
    lateinit var contextResolver: ContentResolver
    private lateinit var rootFolder: File


    @Before
    fun setUp() {
        Mockito.`when`(executor.execute(Mockito.any())).thenAnswer {
            (it.arguments[0] as Runnable).run()
        }
        rootFolder = folder.newFolder()
        Mockito.`when`(context.getExternalFilesDir(null)).thenReturn(rootFolder)
        Mockito.`when`(context.contentResolver).thenReturn(contextResolver)

    }

    @Test
    fun writeStream() {
        val outputFileName = "name"
        val docsFolder = File(rootFolder, "docs")
        Mockito.`when`(fileToUriMapper.getUriFromFile(context, File(docsFolder, outputFileName)))
            .thenReturn(uri)
        val outputFile = folder.newFile("outputfile")
        Mockito.`when`(contextResolver.openOutputStream(uri, "rw"))
            .thenReturn(FileOutputStream(outputFile))
        val byteArray = ByteArray(5)
        byteArray[0] = 0
        byteArray[1] = 1
        byteArray[2] = 2
        byteArray[3] = 3
        byteArray[4] = 4
        providerFileManager.writeStream(outputFileName, ByteArrayInputStream(byteArray))

        Assert.assertEquals(byteArray.size, outputFile.length().toInt())
    }
}