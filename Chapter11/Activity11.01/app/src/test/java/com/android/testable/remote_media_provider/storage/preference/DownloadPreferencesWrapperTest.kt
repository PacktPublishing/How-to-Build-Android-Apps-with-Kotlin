package com.android.testable.remote_media_provider.storage.preference

import android.content.Context
import android.content.SharedPreferences
import com.android.testable.remote_media_provider.R
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DownloadPreferencesWrapperTest {

    @InjectMocks
    lateinit var downloadPreferencesWrapper: DownloadPreferencesWrapper
    @Mock
    lateinit var context: Context
    @Mock
    lateinit var sharedPreferences: SharedPreferences

    @Test
    fun getNumberOfResults() {
        val key = "key"
        val expected = 12
        Mockito.`when`(context.getString(R.string.preference_key_nr_results)).thenReturn(key)
        Mockito.`when`(sharedPreferences.getString(key, DEFAULT_NO_OF_RESULTS.toString())).thenReturn(expected.toString())

        val result = downloadPreferencesWrapper.getNumberOfResults()

        Assert.assertEquals(expected, result)
    }
}