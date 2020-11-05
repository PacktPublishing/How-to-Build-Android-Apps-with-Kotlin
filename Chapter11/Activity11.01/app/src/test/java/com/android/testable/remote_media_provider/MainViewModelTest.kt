package com.android.testable.remote_media_provider

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.android.testable.remote_media_provider.repository.DogUi
import com.android.testable.remote_media_provider.repository.DownloadRepository
import com.android.testable.remote_media_provider.repository.Result
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @InjectMocks
    lateinit var mainViewModel: MainViewModel
    @Mock
    lateinit var downloadRepository: DownloadRepository

    @Test
    fun getDogs() {
        val liveData = MutableLiveData<Result<List<DogUi>>>()
        liveData.postValue(Result.Success(listOf()))
        Mockito.`when`(downloadRepository.loadDogList()).thenReturn(liveData)
        mainViewModel.getDogsLiveData().observeForever {

        }
        mainViewModel.getDogs()
        Assert.assertEquals(liveData.value, mainViewModel.getDogsLiveData().value)
    }

    @Test
    fun downloadFile() {
        val url = "url"
        val expected = MutableLiveData<Result<Unit>>()
        expected.postValue(Result.Success(Unit))
        Mockito.`when`(downloadRepository.downloadFile(url))
            .thenReturn(expected)
        mainViewModel.getDownloadLiveData().observeForever {

        }

        mainViewModel.downloadFile(url)

        Assert.assertEquals(expected.value, mainViewModel.getDownloadLiveData().value)
    }
}