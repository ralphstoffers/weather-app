package ralph.stoffers.weatherapp.model.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
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
    val weatherData = MutableLiveData<List<CurrentWeather>>().apply { value = arrayListOf() }

    fun getCurrentWeather() {
        val list = mutableListOf<CurrentWeather>()
        list.addAll(weatherData.value!!)
        cities.value!!.forEach {
            weatherRepository.getCurrentWeather(it.city, appId)
                .enqueue(object : Callback<WeatherApiResponse> {
                    override fun onResponse(
                        call: Call<WeatherApiResponse>,
                        response: Response<WeatherApiResponse>
                    ) {
                        try {
                            val responsebody = response.body()!!
                            if (responsebody.cod == 200) {
                                list.add(
                                    CurrentWeather(
                                        responsebody.name,
                                        responsebody.main.temp,
                                        responsebody.weather[0].description,
                                        parseWind(responsebody.wind),
                                        responsebody.weather[0].icon
                                    )
                                )
                            }
                        } catch (e: Exception) {
                            Log.e("", e.toString())
                        }
                    }

                    override fun onFailure(call: Call<WeatherApiResponse>, t: Throwable) {
                        Log.e("", t.toString())
                    }
                })
            weatherData.value = list
        }
    }

    private fun parseWind(wind: Wind): String {
        return "bla"
    }
}
