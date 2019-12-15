package com.android.testable.sharedpreferences

import androidx.lifecycle.LiveData
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PreferenceViewModelTest {

    @InjectMocks
    lateinit var preferenceViewModel: PreferenceViewModel
    @Mock
    lateinit var preferenceWrapper: PreferenceWrapper

    @Test
    fun saveText() {
        val text = "text"
        preferenceViewModel.saveText(text)
        Mockito.verify(preferenceWrapper).saveText(text)
    }

    @Test
    fun getText() {
        val text = Mockito.mock(LiveData::class.java)
        Mockito.`when`(preferenceWrapper.getText()).thenReturn(text as LiveData<String>?)
        val result = preferenceWrapper.getText()
        Assert.assertEquals(text, result)
    }
}