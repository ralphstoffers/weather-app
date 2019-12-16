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

class EdityCityActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val cityRepository = CityRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val success = MutableLiveData<Boolean>()
    val error = MutableLiveData<Int?>()

    fun updateCity(city: City) {
        error.value = 0
        mainScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    cityRepository.updateCity(city)
                }
                success.value = true
            } catch (e: Exception) {
                error.value = R.string.update_error
            }
        }
    }
}