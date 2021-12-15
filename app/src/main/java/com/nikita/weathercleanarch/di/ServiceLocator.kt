package com.nikita.weathercleanarch.di

import android.content.Context
import com.nikita.data.api.NetworkModule
import com.nikita.data.repositories.WeatherRemoteDataSourceImpl
import com.nikita.data.repositories.WeatherRepositoryImpl
import com.nikita.weathercleanarch.BuildConfig

object ServiceLocator {

    private val networkModule by lazy {
        NetworkModule()
    }

    @Volatile
    var weatherRepository: WeatherRepositoryImpl? = null

    fun provideWeatherRepository(context: Context): WeatherRepositoryImpl {
        synchronized(this) {
            return weatherRepository ?: createWeatherRepository(context)
        }
    }

    private fun createWeatherRepository(context: Context): WeatherRepositoryImpl {
        val newRepo =
            WeatherRepositoryImpl(
                WeatherRemoteDataSourceImpl(
                    networkModule.createWeatherApi(
                        BuildConfig.WEATHER_APIS_ENDPOINT
                    )
                )
            )

        weatherRepository = newRepo
        return newRepo
    }
}