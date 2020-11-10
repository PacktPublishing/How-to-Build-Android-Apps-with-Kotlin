package com.android.testable.camera

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    companion object {

        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_VIDEO_CAPTURE = 2
    }

    private lateinit var providerFileManager: ProviderFileManager
    private var photoInfo: FileInfo? = null
    private var videoInfo: FileInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        providerFileManager =
            ProviderFileManager(
                applicationContext,
                FileHelper(applicationContext),
                contentResolver,
                Executors.newSingleThreadExecutor(),
                MediaContentHelper()
            )

        photo_button.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoInfo = providerFileManager.generatePhotoUri(System.currentTimeMillis())
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoInfo?.uri)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }

        video_button.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            videoInfo = providerFileManager.generateVideoUri(System.currentTimeMillis())
            intent.putExtra(MediaStore.EXTRA_OUTPUT, videoInfo?.uri)
            startActivityForResult(intent, REQUEST_VIDEO_CAPTURE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                providerFileManager.insertImageToStore(photoInfo)
            }
            REQUEST_VIDEO_CAPTURE -> {
                providerFileManager.insertVideoToStore(videoInfo)
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}
