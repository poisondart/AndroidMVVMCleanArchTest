package com.nikita.weathercleanarch

import android.app.Application
import com.nikita.data.repositories.WeatherRepositoryImpl
import com.nikita.domain.usecases.GetLocationsUseCase
import com.nikita.domain.usecases.GetWeatherUseCase
import com.nikita.weathercleanarch.di.ServiceLocator
import timber.log.Timber

class WeatherCleanArchApplication : Application() {

    private val weatherRepository: WeatherRepositoryImpl
        get() = ServiceLocator.provideWeatherRepository(this)

    val getLocationsUseCase: GetLocationsUseCase
        get() = GetLocationsUseCase(weatherRepository)

    val getWeatherUseCase: GetWeatherUseCase
        get() = GetWeatherUseCase(weatherRepository)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}