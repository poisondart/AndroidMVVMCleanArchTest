package com.nikita.weathercleanarch.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.domain.entities.Location
import com.nikita.weathercleanarch.R
import com.nikita.weathercleanarch.WeatherCleanArchApplication
import kotlinx.android.synthetic.main.fragment_search_locations.*
import timber.log.Timber

class LocationsSearchFragment : Fragment() {

    private val locationsAdapter = LocationsAdapter()

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
        return inflater.inflate(R.layout.fragment_search_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationsAdapter.actionListener = object : LocationsAdapter.ActionClickListener {
            override fun onItemClicked(location: Location) {

            }
        }

        locationsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationsAdapter
        }

        weatherViewModel.locationsDataLoading.observe(viewLifecycleOwner, {
            locationsProgressbar.isVisible = it
        })

        weatherViewModel.locations.observe(viewLifecycleOwner, {
            locationsAdapter.updateData(it)
        })

        weatherViewModel.locationsError.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), R.string.error_occurred, Toast.LENGTH_SHORT).show()
        })

        locationsSearchRequestInputLayout.setEndIconOnClickListener {
            Timber.d("$timberTag inputText Submit Request")
        }

        locationsSearchRequestInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            if ((text?.length?: 0) > 2) {
                weatherViewModel.stopSearch()
                weatherViewModel.getLocations(text.toString())
            }
        }
    }
}