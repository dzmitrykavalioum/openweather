package com.dzmitrykavalioum.oweather.ui.todayweather

import InfoWeather
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzmitrykavalioum.oweather.repository.TodayWeatherRepository

class TodayWeatherViewModel : ViewModel() {

    var infoWeatherLiveData: MutableLiveData<InfoWeather>? = null

    fun getTodayWeather() : LiveData<InfoWeather>? {
        infoWeatherLiveData = TodayWeatherRepository().getTodayWeatherExample()
        return infoWeatherLiveData
    }


}