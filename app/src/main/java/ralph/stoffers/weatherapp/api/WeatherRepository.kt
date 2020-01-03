package ralph.stoffers.weatherapp.api

class WeatherRepository {
    private val weatherApi: WeatherApiService = WeatherApi.createApi()

    fun getCurrentWeather(query: String, appId: String) = weatherApi.getCurrentWeather(query, appId, "metric")

    fun getWeatherForecast(query: String, appId: String) = weatherApi.getWeatherForecast(query, appId, "metric")
}