package com.android.testable.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(val text: String) : Parcelable