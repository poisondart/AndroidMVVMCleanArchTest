package com.nikita.domain.repositories

import com.nikita.domain.entities.Location
import com.nikita.domain.common.Result

interface WeatherRepository {
    suspend fun getLocations(query: String): Result<List<Location>>
    suspend fun getWeather(woeid: Int): Result<Location>
}