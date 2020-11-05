package com.android.testable.camera

import android.net.Uri
import java.io.File

data class FileInfo(
    val uri: Uri,
    val file: File
)