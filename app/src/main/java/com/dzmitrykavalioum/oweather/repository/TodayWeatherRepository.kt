package com.dzmitrykavalioum.oweather.repository

import InfoWeather
import InfoWeatherV2
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dzmitrykavalioum.oweather.api.RetrofitInstance
import com.dzmitrykavalioum.oweather.utils.Constants
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.APP_ID
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.EXAMPLE_CITY
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.METRIC
import com.dzmitrykavalioum.weathapp.api.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class TodayWeatherRepository {

    val infoWeather = MutableLiveData<InfoWeather>()
    val forecastWeather = MutableLiveData<InfoWeatherV2>()

    fun getTodayWeatherExample(): MutableLiveData<InfoWeather>{
        val call = RetrofitInstance.api.getTodayWeatherByCity(EXAMPLE_CITY, METRIC, APP_ID)

        call.enqueue(object: Callback<InfoWeather>{
            override fun onResponse(call: Call<InfoWeather>, response: Response<InfoWeather>) {
                val data= response.body()
                infoWeather.value = data
            }

            override fun onFailure(call: Call<InfoWeather>, t: Throwable) {
                Log.v("TodayWeatherRepository ", t.message.toString())
            }
        })
        return infoWeather
    }

    fun getForecastWeatherByCity(): MutableLiveData<InfoWeatherV2>{
        val call = RetrofitInstance.api.getForecastByCity(EXAMPLE_CITY, METRIC, APP_ID)

        call.enqueue(object: Callback<InfoWeatherV2>{
            override fun onResponse(call: Call<InfoWeatherV2>, response: Response<InfoWeatherV2>) {
                val data= response.body()
                forecastWeather.value = data
            }

            override fun onFailure(call: Call<InfoWeatherV2>, t: Throwable) {
                Log.v("TodayWeatherRepository ", t.message.toString())
            }
        })
        return forecastWeather
    }


}