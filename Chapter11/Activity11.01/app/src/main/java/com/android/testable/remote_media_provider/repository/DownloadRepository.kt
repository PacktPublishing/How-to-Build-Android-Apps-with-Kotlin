package com.android.testable.remote_media_provider.repository

import androidx.lifecycle.LiveData

interface DownloadRepository {

    fun loadDogList(): LiveData<Result<List<DogUi>>>

    fun downloadFile(url: String): LiveData<Result<Unit>>
}