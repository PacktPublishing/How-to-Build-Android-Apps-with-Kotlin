package com.android.testable.remote_media_provider

import android.app.Application
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.android.testable.remote_media_provider.api.DownloadService
import com.android.testable.remote_media_provider.repository.DogMapper
import com.android.testable.remote_media_provider.repository.DownloadRepository
import com.android.testable.remote_media_provider.repository.DownloadRepositoryImpl
import com.android.testable.remote_media_provider.storage.filesystem.FileToUriMapper
import com.android.testable.remote_media_provider.storage.filesystem.ProviderFileHandler
import com.android.testable.remote_media_provider.storage.preference.DownloadPreferencesWrapper
import com.android.testable.remote_media_provider.storage.room.DogDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

class RemoteProviderApplication : Application() {

    lateinit var downloadRepository: DownloadRepository
    lateinit var preferencesWrapper: DownloadPreferencesWrapper

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val downloadService = retrofit.create<DownloadService>(DownloadService::class.java)
        val database =
            Room.databaseBuilder(applicationContext, DogDatabase::class.java, "dog-db")
                .build()
        preferencesWrapper = DownloadPreferencesWrapper(
            this,
            PreferenceManager.getDefaultSharedPreferences(this)
        )
        downloadRepository = DownloadRepositoryImpl(
            preferencesWrapper,
            ProviderFileHandler(
                this,
                FileToUriMapper()
            ),
            downloadService,
            database.dogDao(),
            DogMapper(),
            Executors.newSingleThreadExecutor()
        )
    }
}