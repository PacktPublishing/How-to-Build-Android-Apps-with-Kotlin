package com.android.testable.remote_media_provider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.android.testable.remote_media_provider.repository.DogUi
import com.android.testable.remote_media_provider.repository.DownloadRepository
import com.android.testable.remote_media_provider.repository.Result

class MainViewModel(private val downloadRepository: DownloadRepository) : ViewModel() {

    private val dogsLiveData: MediatorLiveData<Result<List<DogUi>>> by lazy {
        MediatorLiveData<Result<List<DogUi>>>()
    }

    private val downloadResult: MediatorLiveData<Result<Unit>> by lazy {
        MediatorLiveData<Result<Unit>>()
    }

    fun getDogs() {
        dogsLiveData.addSource(downloadRepository.loadDogList()) {
            dogsLiveData.postValue(it)
        }
    }

    fun getDogsLiveData(): LiveData<Result<List<DogUi>>> {
        return dogsLiveData
    }

    fun downloadFile(url: String) {
        downloadResult.addSource(downloadRepository.downloadFile(url)) {
            downloadResult.postValue(it)
        }
    }

    fun getDownloadLiveData(): LiveData<Result<Unit>> = downloadResult

}