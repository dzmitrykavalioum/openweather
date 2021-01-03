package com.dzmitrykavalioum.oweather

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dzmitrykavalioum.oweather.utils.Constants
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.CORRECTION
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.LAT_KEY
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.LON_KEY
import com.dzmitrykavalioum.oweather.utils.GpsLocationHelper
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.todayWeather, R.id.navigationForecast, R.id.navigationWeatherLocation))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        GpsLocationHelper().startListeningUserLocation(this,object : GpsLocationHelper.MyLocationListener{
            override fun onLocationChanged(location: Location) {
                Log.d("MainActivity",location.latitude.toString()+"\t"+ location.longitude.toString())

                var geocoder: Geocoder = Geocoder(this@MainActivity, Locale.ENGLISH)
                var addesses: List<Address> = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                if (addesses.size >= 0) {
                    val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
                    with (sharedPref.edit()) {
                        putLong(LAT_KEY, (location.latitude* CORRECTION).toLong())
                        putLong(LON_KEY, (location.longitude* CORRECTION).toLong())
                        apply()
                    }
                    Log.d("MainActivity", addesses.get(0).locality)
                } else {
                    Log.d("MainActivity", "addresses is empty")

                }

            }

        })
    }
}