package com.android.testable.camera

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File

class FileHelper(private val context: Context) {

    fun getUriFromFile(file: File): Uri {
        return FileProvider.getUriForFile(context, "com.android.testable.camera", file)
    }

    fun getPicturesFolder(): String = Environment.DIRECTORY_PICTURES

    fun getVideosFolder(): String = Environment.DIRECTORY_MOVIES
}