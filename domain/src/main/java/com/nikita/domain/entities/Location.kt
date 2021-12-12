package com.nikita.domain.entities

data class Location(
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String,
    val consolidated_weather: List<Weather>?
)
