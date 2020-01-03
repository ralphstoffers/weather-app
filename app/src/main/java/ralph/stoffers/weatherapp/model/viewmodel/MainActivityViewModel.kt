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
                                        parseWind(responseBody.wind),
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

    private fun parseWind(wind: Wind): String {
        return "bla"
    }
}
