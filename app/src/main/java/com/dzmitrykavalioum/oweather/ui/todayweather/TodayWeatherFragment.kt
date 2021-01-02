package com.dzmitrykavalioum.oweather.ui.todayweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dzmitrykavalioum.oweather.R
import com.dzmitrykavalioum.oweather.utils.Constants.Companion.IMAGE_URL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_today_weather.*

class TodayWeatherFragment : Fragment() {

    private lateinit var todayWeatherViewModel: TodayWeatherViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        todayWeatherViewModel =
            ViewModelProvider(this).get(TodayWeatherViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_today_weather, container, false)
        todayWeatherViewModel.getTodayWeather()!!
            .observe(viewLifecycleOwner, Observer { infoWeather ->
                tvCity.text = infoWeather.name
                tvTemp.text = infoWeather.main.temp.toString() + "°"
                tvHumidity.text = infoWeather.main.humidity.toString() + " %"
                tvPressure.text = infoWeather.main.pressure.toString() + " hPa"
                tvWind.text = infoWeather.wind.speed.toString() + " m/s"
                tvDescription.text = infoWeather.weather[0].description
                tvCloud.text = infoWeather.clouds.all.toString() + " %"
                val imageUrl =
                    IMAGE_URL + infoWeather.weather.get(0).icon + "@2x.png"
                Picasso.get().load(imageUrl).into(ivWeather)
            })

        return root
    }
}