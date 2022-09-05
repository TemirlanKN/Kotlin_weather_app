package com.trasty.kotlin_weather_app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import com.trasty.kotlin_weather_app.MainViewModel
import com.trasty.kotlin_weather_app.R
import com.trasty.kotlin_weather_app.adapters.VpAdapter
import com.trasty.kotlin_weather_app.adapters.WeatherModel
import com.trasty.kotlin_weather_app.databinding.FragmentMainBinding
import org.json.JSONObject
import java.util.jar.Manifest

const val API_KEY = "1eada1e52fad4e409ff33514221706"

class MainFragment : Fragment() {
    private val fList = listOf(
        HoursFragment.newInstance(), DaysFragment.newInstance()
    )
    private val tList = listOf(
        "Hours", "Days"
    )
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentMainBinding
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()
        updateCurrentCard()
        requestWeatherData("Miami")
    }

    private fun init() = with(binding) {
        val adapter = VpAdapter(activity as FragmentActivity, fList)
        vp.adapter = adapter
        TabLayoutMediator(tabLayout, vp) { tab, pos ->
            tab.text = tList[pos]
        }.attach()
    }

    /*
        it's not necessary to call this function(updateCurrentCard)
        after we parse data from server (requestWeatherData)
        cause we use it with listener viewLifecycleOwner
    */
    private fun updateCurrentCard() = with(binding){
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            val maxMinTemp = "${it.minTemp}C/${it.maxTemp}C"
            tvDate.text = it.time
            tvCity.text = it.city
            tvCurrentTemp.text = it.currentTemp
            tvCondition.text = it.condition
            tvMaxMin.text = maxMinTemp
            Picasso.get().load("https:" + it.imageUrl).into(imWeather)

        }
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermission() {
        if (!isPermissionGranted(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun requestWeatherData(city: String) {
        val url = "https://api.weatherapi.com/v1/forecast.json" +
                "?key=$API_KEY" +
                "&q=$city" +
                "&days=3" +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { result ->
                parseWeatherData(result)
            },
            { error ->
                Log.d("MyLog", "Error: $error")
            }
        )
        queue.add(request)

    }

    private fun parseWeatherData(result: String){
        val mainObject = JSONObject(result)
        val item = WeatherModel(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").getString("last_updated"),
            mainObject.getJSONObject("current").getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").getString("temp_c"),
            "",
            "",
            mainObject.getJSONObject("current").getJSONObject("condition").getString("icon"),
            ""
        )
        model.liveDataCurrent.value = item
        Log.d("MyLog", "City: {${item.city}}")
        Log.d("MyLog", "Time: {${item.time}}")
        Log.d("MyLog", "Condition: {${item.condition}}")
        Log.d("MyLog", "Temp: {${item.currentTemp}}")
        Log.d("MyLog", "URL: {${item.imageUrl}}")
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}



