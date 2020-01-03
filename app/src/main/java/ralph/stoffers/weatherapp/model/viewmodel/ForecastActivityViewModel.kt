package ralph.stoffers.weatherapp.model.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.api.WeatherRepository
import ralph.stoffers.weatherapp.model.api.ForecastApiResponse
import ralph.stoffers.weatherapp.model.api.Wind
import ralph.stoffers.weatherapp.model.entity.WeatherForecast
import ralph.stoffers.weatherapp.util.Converters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ForecastActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherRepository = WeatherRepository()
    private val appId = application.applicationContext.getString(R.string.appId)
    val forecastList =
        MutableLiveData<MutableList<WeatherForecast>>().apply { value = mutableListOf() }

    fun getWeatherForecast(city: String) {
        forecastList.apply { value = mutableListOf() }
        weatherRepository.getWeatherForecast(city, appId)
            .enqueue(object : Callback<ForecastApiResponse> {
                override fun onResponse(
                    call: Call<ForecastApiResponse>,
                    response: Response<ForecastApiResponse>
                ) {
                    try {
                        val responseBody = response.body()!!
                        if (responseBody.cod == 200) {
                            val tmp = mutableListOf<WeatherForecast>()
                            responseBody.list.forEach{forecast ->
                                tmp.add(
                                    WeatherForecast(
                                        responseBody.city.name,
                                        forecast.main.temp,
                                        forecast.main.tempMin,
                                        forecast.main.tempMax,
                                        forecast.main.humidity,
                                        forecast.weather[0].description,
                                        formatWind(forecast.wind),
                                        forecast.weather[0].icon,
                                        Date(forecast.dt)
                                    )
                                )
                            }
                            forecastList.apply { value = tmp }
                        }
                    } catch (e: Exception) {
                    }
                }
                override fun onFailure(call: Call<ForecastApiResponse>, t: Throwable) {
                }
            })
    }

    private fun formatWind(wind: Wind): String {
        return String.format(
            "%d Bft, %s",
            Converters.convertMetersPerSecondToBft(wind.speed),
            Converters.convertDegreeToCardinalDirection(wind.deg)
        )
    }
}
