package ralph.stoffers.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "city_table", indices = [Index(value = ["city"], unique = true)])
data class City (
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    @ColumnInfo(name= "city")
    var city: String
)