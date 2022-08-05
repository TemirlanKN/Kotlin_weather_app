package com.trasty.kotlin_weather_app

data class DayItem(
    val city: String,
    val date: String,
    val condition: String,
    val imageUrl: String,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val hours: String
) {
}