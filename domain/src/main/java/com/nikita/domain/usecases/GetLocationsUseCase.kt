package com.nikita.domain.usecases

import com.nikita.domain.repositories.WeatherRepository

class GetLocationsUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(query: String) = weatherRepository.getLocations(query)
}