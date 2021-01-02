package com.dzmitrykavalioum.oweather.ui.forecast

import InfoWeatherV2
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzmitrykavalioum.oweather.repository.TodayWeatherRepository

class ForecastViewModel : ViewModel() {

    var forecastWeatherLiveData: MutableLiveData<InfoWeatherV2>? = null

    fun getForecastWeather(): LiveData<InfoWeatherV2>? {
        forecastWeatherLiveData = TodayWeatherRepository().getForecastWeatherByCity()
        return forecastWeatherLiveData
    }
}