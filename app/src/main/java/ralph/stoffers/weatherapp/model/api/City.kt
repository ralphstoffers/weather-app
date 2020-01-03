package ralph.stoffers.weatherapp.model.api

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("coord") var coord: Coord
)