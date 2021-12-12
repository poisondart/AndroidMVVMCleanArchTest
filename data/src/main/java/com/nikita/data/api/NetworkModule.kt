package com.nikita.data.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {
    private val gson by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        gson
    }

    private fun getRetrofit(endpointURL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endpointURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun createWeatherApi(endpointURL: String): WeatherApi {
        val retrofit = getRetrofit(endpointURL)
        return retrofit.create(WeatherApi::class.java)
    }
}