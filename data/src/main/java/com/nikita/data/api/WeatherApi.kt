package com.nikita.data.api

import com.nikita.domain.entities.Location
import retrofit2.Response
import retrofit2.http.*

interface WeatherApi {
    @GET("api/location/search/")
    suspend fun getLocations(@Query("query") query: String): Response<List<Location>>

    @GET("api/location/{woeid}/")
    suspend fun getWeather(@Path("woeid") woeid: Int): Response<Location>
}