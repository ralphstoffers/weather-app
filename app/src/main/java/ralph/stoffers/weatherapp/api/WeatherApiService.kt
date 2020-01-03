package ralph.stoffers.weatherapp.api

import ralph.stoffers.weatherapp.model.api.WeatherApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/data/2.5/weather")
    fun getCurrentWeather(
        @Query("q") query: String,
        @Query("APPID") appId: String,
        @Query("units") units: String
    ): Call<WeatherApiResponse>
}