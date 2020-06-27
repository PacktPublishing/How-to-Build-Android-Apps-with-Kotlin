package com.example.rxdemo

data class PropertyRecommendation(
    val propertyOwner: String,
    val propertyAddress: String,
    val propertyArea: Double,
    val distanceToNextOutLet: Double,
    val isCarParkingAvailable: Boolean
)