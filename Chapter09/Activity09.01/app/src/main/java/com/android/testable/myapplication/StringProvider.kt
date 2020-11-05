package com.android.testable.myapplication

import android.content.Context

class StringProvider(private val context: Context) {

    fun provideItemString(number: Int): String = context.getString(R.string.item_x, number)

    fun provideYouClickedString(itemText: String) = context.getString(R.string.you_clicked_y, itemText)
}