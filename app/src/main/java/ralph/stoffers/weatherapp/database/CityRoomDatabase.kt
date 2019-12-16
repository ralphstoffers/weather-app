package ralph.stoffers.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ralph.stoffers.weatherapp.model.City

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class CityRoomDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        private const val DATABASE_NAME = "CITY_DATABASE"

        @Volatile
        private var INSTANCE: CityRoomDatabase? = null

        fun getDatabase(context: Context): CityRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(CityRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CityRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}