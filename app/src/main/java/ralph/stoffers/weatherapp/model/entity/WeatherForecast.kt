package ralph.stoffers.weatherapp.model.entity

import java.util.*

data class WeatherForecast(
    val name: String,
    val temp: Double,
    val minTemp: Double,
    val maxTempt: Double,
    val humidity: Int,
    val description: String,
    val wind: String,
    val icon: String,
    val dateTime: Date
)