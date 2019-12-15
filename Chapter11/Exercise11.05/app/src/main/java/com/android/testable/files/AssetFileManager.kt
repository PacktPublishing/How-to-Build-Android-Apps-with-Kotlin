package com.android.testable.files

import android.content.res.AssetManager

class AssetFileManager(private val assetManager: AssetManager) {

    fun getMyAppFileInputStream() = assetManager.open("my-app-file.txt")
}