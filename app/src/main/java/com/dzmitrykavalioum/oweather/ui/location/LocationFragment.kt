package com.dzmitrykavalioum.oweather.ui.location

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dzmitrykavalioum.oweather.R
import com.dzmitrykavalioum.oweather.utils.Constants
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.CORRECTION
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.DEFAULT_LATITUDE
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.DEFAULT_LONGITUDE
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.LAT_KEY
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.LON_KEY
import com.dzmitrykavalioum.oweather.utils.GpsLocationHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_today_weather.*



class LocationFragment : Fragment() {

    private lateinit var locationViewModel: LocationViewModel
    private var latitude: Double? = null
    private var longitude: Double? = null
    private var city:String?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locationViewModel =
            ViewModelProvider(this).get(LocationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_today_weather, container, false)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)


        latitude = ((sharedPref?.getLong(LAT_KEY, DEFAULT_LATITUDE))?.toDouble())?.div(CORRECTION)
        longitude = ((sharedPref?.getLong(LON_KEY, DEFAULT_LONGITUDE))?.toDouble())?.div(CORRECTION)
         Log.d("MainActivity_", latitude.toString()+"\t"+longitude.toString())
        locationViewModel.getTodayWeatherByLoc(latitude!!,longitude!!)
            ?.observe(viewLifecycleOwner, Observer { infoWeather ->
                tvCity.text = infoWeather.name
                tvTemp.text = infoWeather.main.temp.toString() + "°"
                tvHumidity.text = infoWeather.main.humidity.toString() + " %"
                tvPressure.text = infoWeather.main.pressure.toString() + " hPa"
                tvWind.text = infoWeather.wind.speed.toString() + " m/s"
                tvDescription.text = infoWeather.weather[0].description
                tvCloud.text = infoWeather.clouds.all.toString() + " %"
                val imageUrl =
                    Constants.IMAGE_URL + infoWeather.weather.get(0).icon + "@2x.png"
                Picasso.get().load(imageUrl).into(ivWeather)

            })

        return root
    }

    fun getLocation() {
        var myLocation: Location? = null
        GpsLocationHelper().startListeningUserLocation(requireContext(),
            object : GpsLocationHelper.MyLocationListener {
                override fun onLocationChanged(location: Location) {
                    latitude = location.latitude
                    longitude = location.longitude
                    Log.d(
                        "LocationFragment",
                        location.latitude.toString() + "\t" + location.longitude.toString()
                    )
                    myLocation = location
                }

            })

    }
}