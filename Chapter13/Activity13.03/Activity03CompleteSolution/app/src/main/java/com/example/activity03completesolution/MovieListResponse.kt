package com.example.activity03completesolution

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

data class MovieListResponse(
    val page: String,
    val results: ArrayList<Movie>
)