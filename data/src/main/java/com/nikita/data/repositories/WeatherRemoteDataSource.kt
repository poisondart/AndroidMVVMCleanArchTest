package com.nikita.data.repositories

import com.nikita.domain.common.Result
import com.nikita.domain.entities.Location

interface WeatherRemoteDataSource {
    suspend fun getLocations(query: String): Result<List<Location>>
    suspend fun getWeather(woeid: Int): Result<Location>
}