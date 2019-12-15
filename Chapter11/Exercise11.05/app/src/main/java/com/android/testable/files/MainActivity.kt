package com.android.testable.files

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

private const val REQUEST_CODE_CREATE_DOC = 10

class MainActivity : AppCompatActivity() {

    private val assetFileManager: AssetFileManager by lazy {
        AssetFileManager(applicationContext.assets)
    }
    private val providerFileManager: ProviderFileManager by lazy {
        ProviderFileManager(
            applicationContext,
            FileToUriMapper(),
            Executors.newSingleThreadExecutor()
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity_main_file_provider.setOnClickListener {
            val newFileName = "Copied.txt"
            providerFileManager.writeStream(newFileName, assetFileManager.getMyAppFileInputStream())
        }
        activity_main_saf.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TITLE, "Copied.txt")
                }
                startActivityForResult(intent, REQUEST_CODE_CREATE_DOC)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_CREATE_DOC && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                val newFileName = "Copied.txt"
                providerFileManager.writeStreamFromUri(
                    newFileName,
                    assetFileManager.getMyAppFileInputStream(),
                    uri
                )
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
