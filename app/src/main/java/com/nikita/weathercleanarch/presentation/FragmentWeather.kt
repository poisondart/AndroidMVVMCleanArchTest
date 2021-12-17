package com.nikita.weathercleanarch.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.weathercleanarch.R
import com.nikita.weathercleanarch.WeatherCleanArchApplication
import kotlinx.android.synthetic.main.fragment_weather.*
import timber.log.Timber

class FragmentWeather : Fragment() {

    private val weatherAdapter = WeatherAdapter()

    private val timberTag = this::class.java.simpleName

    private val weatherViewModel: WeatherViewModel by viewModels {
        WeatherViewModel.WeatherModelFactory(
            (requireActivity().application as WeatherCleanArchApplication).getLocationsUseCase,
            (requireActivity().application as WeatherCleanArchApplication).getWeatherUseCase
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = weatherAdapter
        }

        weatherViewModel.weatherDataLoading.observe(viewLifecycleOwner, {
            weatherProgressbar.isVisible = it
        })

        weatherViewModel.weather.observe(viewLifecycleOwner, {
            weatherAdapter.initData(it.consolidated_weather ?: return@observe)
        })

        weatherViewModel.weatherError.observe(viewLifecycleOwner, {
            Timber.d("$timberTag $it")
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        weatherViewModel.getWeather(arguments?.getInt("woeid")?: return)

    }
}