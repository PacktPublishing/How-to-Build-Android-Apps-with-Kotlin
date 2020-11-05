package com.android.testable.camera

import android.content.Context
import android.os.Environment
import java.io.File

class ProviderFileManager(
    private val context: Context,
    private val fileHelper: FileHelper
) {

    fun generatePhotoUri(time: Long): FileInfo {
        val file = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "img_$time.jpg"
        )
        return FileInfo(fileHelper.getUriFromFile(file), file)
    }

    fun generateVideoUri(time: Long): FileInfo {
        val file = File(
            context.getExternalFilesDir(Environment.DIRECTORY_MOVIES),
            "video_$time.mp4"
        )
        return FileInfo(fileHelper.getUriFromFile(file), file)
    }
}