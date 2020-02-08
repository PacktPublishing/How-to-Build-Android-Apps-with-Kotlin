package com.android.testable.myapplication

import android.content.Context
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StringProviderTest {

    @InjectMocks
    lateinit var stringProvider: StringProvider
    @Mock
    lateinit var context: Context

    @Test
    fun provideItemString() {
        val number = 5
        val expected = "expected"
        whenever(context.getString(R.string.item_x, number)).thenReturn(expected)

        val result = stringProvider.provideItemString(number)

        assertEquals(expected, result)
    }

    @Test
    fun provideYouClickedString() {
        val itemText = "itemText"
        val expected = "expected"
        whenever(context.getString(R.string.you_clicked_y, itemText)).thenReturn(expected)

        val result = stringProvider.provideYouClickedString(itemText)

        assertEquals(expected, result)
    }
}