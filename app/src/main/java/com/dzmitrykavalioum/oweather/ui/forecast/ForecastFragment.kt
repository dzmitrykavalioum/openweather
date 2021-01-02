package com.dzmitrykavalioum.oweather.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzmitrykavalioum.oweather.R
import com.dzmitrykavalioum.oweather.adapters.WeatherAdapter
import kotlinx.android.synthetic.main.fragment_forecast.*

class ForecastFragment : Fragment() {

    private lateinit var forecastViewModel: ForecastViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        forecastViewModel =
                ViewModelProvider(this).get(ForecastViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_forecast, container, false)

        forecastViewModel.getForecastWeather()!!.observe(viewLifecycleOwner, Observer { forecastWeather->
            if (activity!=null&& isAdded) {
                rvForecast.adapter = WeatherAdapter(forecastWeather.list)
                rvForecast.layoutManager = LinearLayoutManager(requireContext())
                rvForecast.setHasFixedSize(true)

            }
        })
        return root
    }
}