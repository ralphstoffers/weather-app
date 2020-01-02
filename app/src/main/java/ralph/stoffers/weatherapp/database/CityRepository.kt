package ralph.stoffers.weatherapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import ralph.stoffers.weatherapp.model.entity.City

class CityRepository (context: Context) {
    private val cityDao: CityDao

    init {
        val database = CityRoomDatabase.getDatabase(context)
        cityDao = database!!.cityDao()
    }

    fun getAllCities(): LiveData<List<City>> {
        return cityDao.getAllCities()
    }

    suspend fun insertCity(city: City) {
        cityDao.insertCity(city)
    }

    suspend fun updateCity(city: City) {
        cityDao.updateCity(city)
    }

    suspend fun deleteCity(city: City) {
        cityDao.deleteCity(city)
    }
}