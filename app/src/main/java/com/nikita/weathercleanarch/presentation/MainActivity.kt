package com.nikita.weathercleanarch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.nikita.weathercleanarch.R

class MainActivity : AppCompatActivity(), LocationsSearchFragment.LocationClickListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onLocationClicked(woeid: Int) {
        val bundle = bundleOf("woeid" to woeid)
        navController.navigate(R.id.action_fragmentSearchLocations_to_fragmentWeather, bundle)
    }
}