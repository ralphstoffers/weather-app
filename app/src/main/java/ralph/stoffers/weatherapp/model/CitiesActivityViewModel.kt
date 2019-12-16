package ralph.stoffers.weatherapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ralph.stoffers.weatherapp.R
import ralph.stoffers.weatherapp.database.CityRepository

class CitiesActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val cityRepository = CityRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val success = MutableLiveData<Int?>()
    val error = MutableLiveData<Int?>()
    val cities = cityRepository.getAllCities()

    fun insertCity(cityName: String) {
        error.value = 0
        success.value = 0
        if (cityName.isNotEmpty()) {
            mainScope.launch {
                try {
                    withContext(Dispatchers.IO) {
                        cityRepository.insertCity(City(city = cityName))
                    }
                    success.value = R.string.insert_success
                } catch (e: Exception) {
                    error.value = R.string.insert_error
                }

            }
        }
    }

    fun insertCity(city: City) {
        error.value = 0
        success.value = 0
        mainScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    cityRepository.insertCity(city)
                }
            } catch (e: Exception) {
            }
        }
    }


    fun deleteCity(city: City) {
        error.value = 0
        success.value = 0
        mainScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    cityRepository.deleteCity(city)
                }
                success.value = R.string.delete_success
            } catch (e: Exception) {
                error.value = R.string.delete_error
            }
        }
    }
}