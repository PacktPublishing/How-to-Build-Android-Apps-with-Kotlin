package com.example.tvguide.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tvshows",  primaryKeys = [("id")])
data class TVShow(
    val backdrop_path: String? = "",
    val first_air_date: String = "",
    val id: Int = 0,
    val name: String = "",
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Float = 0f,
    val poster_path: String? = "",
    val vote_average: Float = 0f,
    val vote_count: Int = 0
) : Parcelable


