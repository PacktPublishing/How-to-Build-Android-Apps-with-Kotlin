package com.android.testable.myapplication

import androidx.test.espresso.idling.CountingIdlingResource
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ItemGeneratorTest {

    private lateinit var itemGenerator: ItemGenerator
    @Mock
    lateinit var timer: Timer
    @Mock
    lateinit var stringProvider: StringProvider
    private val initialDelay = 5L
    @Mock
    lateinit var countingIdlingResource: CountingIdlingResource

    @Before
    fun setUp() {
        itemGenerator = ItemGenerator(timer, stringProvider, initialDelay, countingIdlingResource)
    }

    @Test
    fun generateItemsAsync() {
        val spy = spy(itemGenerator)
        val callback = mock<(List<Item>) -> Unit>()
        val itemCount = 10
        val items = listOf(Item("1"), Item("2"))
        doReturn(items).whenever(spy).generateItems(itemCount)
        whenever(timer.schedule(any(), eq(initialDelay))).thenAnswer {
            (it.arguments[0] as TimerTask).run()
        }

        spy.generateItemsAsync(itemCount, callback)

        verify(callback).invoke(items)
        verify(countingIdlingResource).increment()
        verify(countingIdlingResource).decrement()
    }

    @Test
    fun generateItems() {
        val itemCount = 10
        val expected = mutableListOf<Item>()
        for (i in 1..itemCount) {
            val itemText = "itemText$i"
            whenever(stringProvider.provideItemString(i)).thenReturn(itemText)
            expected.add(Item(itemText))
        }

        val result = itemGenerator.generateItems(itemCount)

        assertEquals(expected, result)
    }
}