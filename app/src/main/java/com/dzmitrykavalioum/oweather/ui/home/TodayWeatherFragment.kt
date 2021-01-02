package com.dzmitrykavalioum.oweather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dzmitrykavalioum.oweather.R
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

        todayWeatherViewModel.getTodayWeather()!!.observe(viewLifecycleOwner, Observer {infoWeather->
            tvCity.text = infoWeather.name
        })

        return root
    }
}