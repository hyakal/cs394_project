# Weather Forecast Application

## Overview

The Weather Forecast Application works for cities in Turkey. The app is designed to be visually appealing, intuitive, and responsive, incorporating Material Design principles and smooth animations.

Users can:
- View the current weather, hourly forecasts, and daily forecasts for a selected city.
- Navigate between cities and weather details with ease.

---

## Features

- **City Weather Overview**: Select a city from a list to view its weather data.
- **Hourly Forecast**: Displays weather data for the next 24 hours starting from the current time.
- **Daily Forecast**: Provides weather predictions for the next 5 days.
- **Material Design UI**: Styled with Material Design components such as `CardView`, providing a modern and polished look.
- **Dark and Light Modes**: Supports both dark and light themes for better readability.
- **RecyclerView Animations**: Smooth animations for RecyclerView items using layout animation.
- **Fragment Transitions**: Custom animations for transitions between screens.

---

## Technologies and Libraries Used

- **Language**: Kotlin
- **Frameworks**:
  - Retrofit2 (HTTP client for API integration)
  - Moshi (JSON parsing)
- **Design**:
  - Material Design Components
  - RecyclerView with `CardView` for weather items
- **API**: [WeatherAPI.com](https://www.weatherapi.com) for fetching weather data.

---


## Installation

### Prerequisites
- **Android Studio**: Latest version installed.
- **WeatherAPI Key**: Register at [WeatherAPI.com](https://www.weatherapi.com) to obtain your API key.

### Setup Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/weather-forecast-app.git
   cd weather-forecast-app
   ```

2. Open the project in Android Studio.

3. Add your API key in the `WeatherViewModel.kt` file:
   ```WeatherViewModel.kt
   private val apiKey="your_weather_api_key"
   ```

4. Build and run the app on an emulator or physical device.

---

## Usage

1. Launch the app.
2. Select a city from the list.
3. View:
   - **Current Weather**: Displays the current temperature, condition, and high/low temperatures.
   - **Hourly Forecast**: A horizontally scrollable RecyclerView showing hourly weather data for the next 24 hours.
   - **Daily Forecast**: A vertically scrollable RecyclerView displaying weather predictions for the next 5 days.

## Contributions

- **hyakal: Design of the 2nd screen such as RecyclerView and MaterialCardView implementations. Implementation of ViewModel, LiveData. Integration of WeatherAPI by utilizing Repository Pattern.

## Credits

- **WeatherAPI.com**: For providing free weather data.
- **Icons**: Icons are retrived from: https://fonts.google.com
