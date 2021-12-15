package com.nikita.weathercleanarch.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.domain.entities.Location
import com.nikita.weathercleanarch.R
import kotlinx.android.synthetic.main.fragment_search_locations.*
import timber.log.Timber

class LocationsSearchFragment : Fragment() {

    private val locationsAdapter = LocationsAdapter()

    private val timberTag = this::class.java.simpleName

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

        locationsSearchRequestInputLayout.setEndIconOnClickListener {
            Timber.d("$timberTag inputText Submit Request")
        }
    }
}