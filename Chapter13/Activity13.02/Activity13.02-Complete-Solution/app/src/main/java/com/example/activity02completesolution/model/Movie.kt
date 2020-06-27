package com.example.activity02completesolution.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val release_date: String,
    val original_language: String,
    val overview: String,
    val popularity: Double
): Parcelable