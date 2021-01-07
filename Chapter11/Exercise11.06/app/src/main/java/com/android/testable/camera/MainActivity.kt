package com.android.testable.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    companion object {

        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_VIDEO_CAPTURE = 2
        private const val REQUEST_EXTERNAL_STORAGE = 3
    }

    private lateinit var providerFileManager: ProviderFileManager
    private var photoInfo: FileInfo? = null
    private var videoInfo: FileInfo? = null
    private var isCapturingVideo = false

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

        findViewById<Button>(R.id.photo_button).setOnClickListener {
            isCapturingVideo = false
            checkStoragePermission {
                openImageCapture()
            }
        }

        findViewById<Button>(R.id.video_button).setOnClickListener {
            isCapturingVideo = true
            checkStoragePermission {
                openVideoCapture()
            }
        }
    }

    private fun checkStoragePermission(onPermissionGranted: () -> Unit) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q) {
            when (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )) {
                PackageManager.PERMISSION_GRANTED -> {
                    onPermissionGranted()
                }
                else -> {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_EXTERNAL_STORAGE
                    )
                }
            }
        } else {
            onPermissionGranted()
        }
    }

    private fun openImageCapture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoInfo = providerFileManager.generatePhotoUri(System.currentTimeMillis())
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoInfo?.uri)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    private fun openVideoCapture() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        videoInfo = providerFileManager.generateVideoUri(System.currentTimeMillis())
        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoInfo?.uri)
        startActivityForResult(intent, REQUEST_VIDEO_CAPTURE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    if (isCapturingVideo) {
                        openVideoCapture()
                    } else {
                        openImageCapture()
                    }
                }
                return
            }

            else -> {
            }
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
