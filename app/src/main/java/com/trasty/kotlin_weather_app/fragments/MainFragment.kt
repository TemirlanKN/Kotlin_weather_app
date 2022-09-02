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
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayoutMediator
import com.trasty.kotlin_weather_app.R
import com.trasty.kotlin_weather_app.adapters.VpAdapter
import com.trasty.kotlin_weather_app.databinding.FragmentMainBinding
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
        requestWeatherData("Miami")
    }

    private fun init() = with(binding){
        val adapter = VpAdapter(activity as FragmentActivity, fList)
        vp.adapter = adapter
        TabLayoutMediator(tabLayout, vp){
            tab, pos -> tab.text = tList[pos]
        }.attach()
    }

    private fun permissionListener(){
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermission(){
        if(!isPermissionGranted(android.Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionListener()
            pLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun requestWeatherData(city:String){
        val url = "https://api.weatherapi.com/v1/forecast.json" +
                "?key=$API_KEY" +
                "&q=$city" +
                "&days=3" +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                    result -> Log.d("MyLog", "Error: $result")
            },
            {
                    error -> Log.d("MyLog", "Error: $error")
            }
            )
        queue.add(request)

    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}