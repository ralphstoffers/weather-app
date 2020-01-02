package ralph.stoffers.weatherapp.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "city_table", indices = [Index(value = ["city"], unique = true)])
data class City (
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    @ColumnInfo(name= "city")
    var city: String
) : Parcelable