package com.dzmitrykavalioum.oweather.ui.location

import InfoWeather
import android.app.Application
import android.location.Location
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dzmitrykavalioum.oweather.repository.TodayWeatherRepository
import com.dzmitrykavalioum.oweather.utils.GpsLocationHelper

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    var infoWeatherLiveData: MutableLiveData<InfoWeather>? = null

    fun getTodayWeatherByLoc(city:String) : LiveData<InfoWeather>?{
        infoWeatherLiveData = TodayWeatherRepository()
            .getTodayWeatherByCity(city)
        return infoWeatherLiveData
    }

    fun getTodayWeatherByLocation() : LiveData<InfoWeather>? {
        GpsLocationHelper().startListeningUserLocation(getApplication(),
            object :GpsLocationHelper.MyLocationListener{
                override fun onLocationChanged(location: Location) {
                    infoWeatherLiveData = TodayWeatherRepository()
                        .getTodayWeatherByLocation(location.latitude,location.longitude)
                    Log.d("LocationFragment", location.latitude.toString()+"\t"+location.longitude.toString())
                }

            })

        return infoWeatherLiveData
    }

}