package com.nikita.weathercleanarch.presentation

import androidx.lifecycle.*
import com.nikita.domain.common.Result
import com.nikita.domain.entities.Location
import com.nikita.domain.usecases.GetLocationsUseCase
import com.nikita.domain.usecases.GetWeatherUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _locationsDataLoading = MutableLiveData(false)
    val locationsDataLoading: LiveData<Boolean> = _locationsDataLoading

    private val _weatherDataLoading = MutableLiveData(false)
    val weatherDataLoading: LiveData<Boolean> = _weatherDataLoading

    private val _locationsError = MutableLiveData<String>()
    val locationsError: LiveData<String> = _locationsError

    private val _weatherError = MutableLiveData<String>()
    val weatherError: LiveData<String> = _weatherError

    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> = _locations

    private val _weather = MutableLiveData<Location>()
    val weather: LiveData<Location> = _weather

    private var _job: Job? = null

    fun stopSearch() {
        _job?.cancel()
    }

    fun getLocations(query: String) {
        _job = viewModelScope.launch {
            _locationsDataLoading.postValue(true)
            when(val locationsResult = getLocationsUseCase.invoke(query)) {
                is Result.Success -> {
                    _locationsDataLoading.postValue(false)
                    _locations.postValue(locationsResult.data)
                }
                is Result.Error -> {
                    _locationsDataLoading.postValue(false)
                    _locationsError.postValue(locationsResult.exception.message)
                }
            }
        }
    }

    fun getWeather(woeid: Int) {
        viewModelScope.launch {
            _weatherDataLoading.postValue(true)
            when(val weatherResult = getWeatherUseCase.invoke(woeid)) {
                is Result.Success -> {
                    _weatherDataLoading.postValue(false)
                    _weather.postValue(weatherResult.data)
                }
                is Result.Error -> {
                    _weatherDataLoading.postValue(false)
                    _weatherError.postValue(weatherResult.exception.message)
                }
            }
        }
    }

    class WeatherModelFactory(
        private val getLocationsUseCase: GetLocationsUseCase,
        private val getWeatherUseCase: GetWeatherUseCase
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherViewModel(
                getLocationsUseCase,
                getWeatherUseCase
            ) as T
        }
    }
}