package com.android.testable.sharedpreferences

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PreferenceWrapperTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var preferenceWrapper: PreferenceWrapper
    @Mock
    lateinit var sharedPreferences: SharedPreferences
    @Mock
    lateinit var editor: SharedPreferences.Editor

    @Before
    fun setUp() {
        val text = "text"
        Mockito.`when`(sharedPreferences.getString(KEY_TEXT, "")).thenReturn(text)
        Mockito.`when`(sharedPreferences.registerOnSharedPreferenceChangeListener(Mockito.any()))
            .thenAnswer {
                (it.arguments[0] as SharedPreferences.OnSharedPreferenceChangeListener).onSharedPreferenceChanged(
                    sharedPreferences,
                    KEY_TEXT
                )
            }

        preferenceWrapper = PreferenceWrapper(sharedPreferences)
        val result = preferenceWrapper.getText()
        Assert.assertEquals(text, result.value!!)

        Mockito.`when`(sharedPreferences.edit()).thenReturn(editor)
        Mockito.`when`(editor.putString(Mockito.anyString(), Mockito.anyString()))
            .thenReturn(editor)
    }

    @Test
    fun saveText() {
        val text = "text"
        preferenceWrapper.saveText(text)
        val inOrder = Mockito.inOrder(sharedPreferences, editor)
        inOrder.verify(sharedPreferences).edit()
        inOrder.verify(editor).putString(KEY_TEXT, text)
        inOrder.verify(editor).apply()
    }

    @Test
    fun getText() {
        val text = "text"
        Mockito.`when`(sharedPreferences.getString(KEY_TEXT, "")).thenReturn(text)
        val result = preferenceWrapper.getText()
        Assert.assertEquals(text, result.value!!)
    }
}