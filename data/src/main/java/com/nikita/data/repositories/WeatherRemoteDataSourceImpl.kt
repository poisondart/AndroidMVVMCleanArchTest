package com.nikita.data.repositories

import com.nikita.data.api.WeatherApi
import com.nikita.domain.common.Result
import com.nikita.domain.entities.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRemoteDataSourceImpl(private val service: WeatherApi): WeatherRemoteDataSource {
    override suspend fun getLocations(query: String): Result<List<Location>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getLocations(query)
                if (response.isSuccessful) {
                    return@withContext Result.Success(response.body()!!)
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(Exception(e))
            }
        }

    override suspend fun getWeather(woeid: Int): Result<Location> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getWeather(woeid)
                if (response.isSuccessful) {
                    return@withContext Result.Success(response.body()!!)
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(Exception(e))
            }
        }
}