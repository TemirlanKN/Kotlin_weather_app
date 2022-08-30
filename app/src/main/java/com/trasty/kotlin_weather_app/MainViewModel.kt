package com.trasty.kotlin_weather_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trasty.kotlin_weather_app.adapters.WeatherModel

class MainViewModel : ViewModel() {
    val liveDataCurrent = MutableLiveData<WeatherModel>()
    val liveDataList = MutableLiveData<List<WeatherModel>>()
}