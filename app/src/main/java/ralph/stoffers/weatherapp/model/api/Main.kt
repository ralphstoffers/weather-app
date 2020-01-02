package ralph.stoffers.weatherapp.model.api

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp") var temp: Double,
    @SerializedName("pressure") var pressure: Int,
    @SerializedName("humidity") var humidity: Int,
    @SerializedName("temp_min") var tempMin: Double,
    @SerializedName("temp_max") var tempMax: Double
)
