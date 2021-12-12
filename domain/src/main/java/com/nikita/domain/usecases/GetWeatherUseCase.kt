package com.nikita.domain.usecases

import com.nikita.domain.repositories.WeatherRepository

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(woeid: Int) = weatherRepository.getWeather(woeid)
}