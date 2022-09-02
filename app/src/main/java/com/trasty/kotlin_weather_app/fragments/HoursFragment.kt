package com.trasty.kotlin_weather_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.trasty.kotlin_weather_app.R
import com.trasty.kotlin_weather_app.adapters.WeatherAdapter
import com.trasty.kotlin_weather_app.adapters.WeatherModel
import com.trasty.kotlin_weather_app.databinding.FragmentHoursBinding
import java.util.zip.Inflater

class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        rcView.adapter = adapter

        val list = listOf(
            WeatherModel(
                "", "09:00", "Sunny",
                "25C", "", "", "", ""),
            WeatherModel(
                "", "12:00", "Sunny",
            "35C", "", "", "", "") ,
            WeatherModel(
                "", "15:00", "Sunny",
            "33C", "", "", "", "")
        )

        adapter.submitList(list)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}