package com.android.testable.camera

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

class FileHelper(private val context: Context) {

    fun getUriFromFile(file: File): Uri {
        return FileProvider.getUriForFile(context, "com.android.testable.camera", file)
    }
}