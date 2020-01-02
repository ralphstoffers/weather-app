package ralph.stoffers.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ralph.stoffers.weatherapp.model.entity.City

@Dao
interface CityDao {
    @Insert
    suspend fun insertCity(city: City)

    @Query("SELECT * FROM city_table")
    fun getAllCities(): LiveData<List<City>>

    @Delete
    suspend fun deleteCity(city: City)

    @Update
    suspend fun updateCity(city: City)
}