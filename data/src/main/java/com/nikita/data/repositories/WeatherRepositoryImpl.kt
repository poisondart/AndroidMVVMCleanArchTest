package com.nikita.data.repositories

import com.nikita.domain.common.Result
import com.nikita.domain.entities.Location
import com.nikita.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(private val remoteDataSource: WeatherRemoteDataSource): WeatherRepository {
    override suspend fun getLocations(query: String): Result<List<Location>> {
        return remoteDataSource.getLocations(query)
    }

    override suspend fun getWeather(woeid: Int): Result<Location> {
        return remoteDataSource.getWeather(woeid)
    }
}