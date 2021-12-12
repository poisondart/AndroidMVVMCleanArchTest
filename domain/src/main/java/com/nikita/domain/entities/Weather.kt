package com.nikita.domain.entities

data class Weather(
    val id: Long,
    val weather_state_name: String,
    val weather_state_abbr: String,
    val applicable_date: String
)
