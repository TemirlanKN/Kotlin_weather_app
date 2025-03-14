# Weather App with Location Services

## Overview

A modern Android weather application built with Kotlin that provides real-time weather information based on user location or city search. The app features a clean Material Design UI, location-based weather updates, and detailed weather forecasts.

## Features

- Real-time weather data using weather API
- Location-based weather information
- City search functionality
- Daily and hourly weather forecasts
- Interactive UI with ViewPager2 and TabLayout
- Material Design implementation
- Dynamic weather icon loading
- Temperature and condition displays

## Tech Stack

- **Language:** Kotlin
- **Minimum SDK:** 23
- **Target SDK:** 32
- **Libraries:**
  - Volley for network requests
  - Picasso for image loading
  - Google Play Services Location
  - AndroidX components
  - Material Design components
  - ViewBinding

## Architecture

- MVVM architecture pattern
- LiveData for data observation
- ViewModel for data handling
- Fragment-based UI

## Project Structure

```
app/
├── src/main/
│   ├── java/com/trasty/kotlin_weather_app/
│   │   ├── adapters/
│   │   │   ├── WeatherAdapter.kt
│   │   │   └── WeatherModel.kt
│   │   ├── fragments/
│   │   │   ├── MainFragment.kt
│   │   │   ├── HoursFragment.kt
│   │   │   └── DaysFragment.kt
│   │   ├── MainActivity.kt
│   │   ├── MainViewModel.kt
│   │   └── DialogManager.kt
│   └── res/
│       ├── layout/
│       ├── drawable/
│       └── values/
```

## Setup Instructions

### Prerequisites

- Android Studio Arctic Fox or later
- JDK 11
- Android SDK 32
- Google Play Services

### Installation

1. Clone the repository

```bash
git clone [repository-url]
```

2. Open project in Android Studio

3. Add your Weather API key in MainActivity:

```kotlin
const val API_KEY = "your_api_key_here"
```

4. Build and run the app

## Permissions

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
```

## Key Components

### MainFragment

- Handles main weather display
- Location services integration
- Weather data fetching
- UI state management

### WeatherModel

```kotlin
data class WeatherModel(
    val city: String,
    val time: String,
    val condition: String,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val imageUrl: String,
    val hours: String
)
```

## Features Implementation

- Location Services using FusedLocationProviderClient
- Weather API integration with Volley
- Image loading with Picasso
- ViewPager2 with TabLayout for forecast views
- Material Design CardView for weather information
- Dynamic permission handling

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a pull request

## License

[Specify License]

## Acknowledgments

- Weather API provider
- Google Play Services
- Android Open Source Community
