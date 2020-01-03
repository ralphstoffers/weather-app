package ralph.stoffers.weatherapp.model.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.api.WeatherRepository
import ralph.stoffers.weatherapp.database.CityRepository
import ralph.stoffers.weatherapp.model.api.WeatherApiResponse
import ralph.stoffers.weatherapp.model.api.Wind
import ralph.stoffers.weatherapp.model.entity.City
import ralph.stoffers.weatherapp.model.entity.CurrentWeather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val cityRepository = CityRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val weatherRepository = WeatherRepository()
    private val appId = application.applicationContext.getString(R.string.appId)
    val cities = cityRepository.getAllCities()
    val weatherList = MutableLiveData<MutableList<CurrentWeather>>().apply { value = mutableListOf()}

    fun getCurrentWeather(cities: List<City>) {
        weatherList.apply { value = mutableListOf() }
        cities.forEach {
            weatherRepository.getCurrentWeather(it.city, appId)
                .enqueue(object : Callback<WeatherApiResponse> {
                    override fun onResponse(
                        call: Call<WeatherApiResponse>,
                        response: Response<WeatherApiResponse>
                    ) {
                        try {
                            val responseBody = response.body()!!
                            if (responseBody.cod == 200) {
                                val tmp = mutableListOf<CurrentWeather>()
                                tmp.addAll(weatherList.value!!)
                                tmp.add(
                                    CurrentWeather(
                                        responseBody.name,
                                        responseBody.main.temp,
                                        responseBody.weather[0].description,
                                        formatWind(responseBody.wind),
                                        responseBody.weather[0].icon
                                    )
                                )
                                weatherList.apply { value = tmp }
                            }
                        } catch (e: Exception) {
                            Log.e("err", e.message!!)
                        }
                    }

                    override fun onFailure(call: Call<WeatherApiResponse>, t: Throwable) {
                        Log.e("err", t.message!!)
                    }
                })
        }
    }

    private fun formatWind(wind: Wind): String {

        return String.format("%d Bft, %s",
            convertMetersPerSecondToBft(wind.speed),
            convertDegreeToCardinalDirection(wind.deg))
    }

    fun convertMetersPerSecondToBft(speed: Double): Int {
        return when {
            speed <= 0.2 -> 0
            speed <= 1.5 -> 1
            speed <= 3.3 -> 2
            speed <= 5.4 -> 3
            speed <= 7.9 -> 4
            speed <= 10.7 -> 5
            speed <= 13.8 -> 6
            speed <= 17.1 -> 7
            speed <= 20.7 -> 8
            speed <= 24.4 -> 9
            speed <= 28.4 -> 10
            speed <= 32.6 -> 11
            else -> 12
        }
    }

    fun convertDegreeToCardinalDirection(directionInDegrees: Int): String {
        val cardinalDirection: String?
        if (directionInDegrees >= 348.75 && directionInDegrees <= 360 || directionInDegrees >= 0 && directionInDegrees <= 11.25) {
            cardinalDirection = "N"
        } else if (directionInDegrees >= 11.25 && directionInDegrees <= 33.75) {
            cardinalDirection = "NNE"
        } else if (directionInDegrees >= 33.75 && directionInDegrees <= 56.25) {
            cardinalDirection = "NE"
        } else if (directionInDegrees >= 56.25 && directionInDegrees <= 78.75) {
            cardinalDirection = "ENE"
        } else if (directionInDegrees >= 78.75 && directionInDegrees <= 101.25) {
            cardinalDirection = "E"
        } else if (directionInDegrees >= 101.25 && directionInDegrees <= 123.75) {
            cardinalDirection = "ESE"
        } else if (directionInDegrees >= 123.75 && directionInDegrees <= 146.25) {
            cardinalDirection = "SE"
        } else if (directionInDegrees >= 146.25 && directionInDegrees <= 168.75) {
            cardinalDirection = "SSE"
        } else if (directionInDegrees >= 168.75 && directionInDegrees <= 191.25) {
            cardinalDirection = "S"
        } else if (directionInDegrees >= 191.25 && directionInDegrees <= 213.75) {
            cardinalDirection = "SSW"
        } else if (directionInDegrees >= 213.75 && directionInDegrees <= 236.25) {
            cardinalDirection = "SW"
        } else if (directionInDegrees >= 236.25 && directionInDegrees <= 258.75) {
            cardinalDirection = "WSW"
        } else if (directionInDegrees >= 258.75 && directionInDegrees <= 281.25) {
            cardinalDirection = "W"
        } else if (directionInDegrees >= 281.25 && directionInDegrees <= 303.75) {
            cardinalDirection = "WNW"
        } else if (directionInDegrees >= 303.75 && directionInDegrees <= 326.25) {
            cardinalDirection = "NW"
        } else if (directionInDegrees >= 326.25 && directionInDegrees <= 348.75) {
            cardinalDirection = "NNW"
        } else {
            cardinalDirection = "?"
        }

        return cardinalDirection
    }
}
