package com.android.testable.remote_media_provider.storage.filesystem

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

class FileToUriMapper {

    fun getUriForFile(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "com.android.testable.remote_media_provider",
            file
        )
    }
}