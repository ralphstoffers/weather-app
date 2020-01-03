package ralph.stoffers.weatherapp.model.api

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("dt") var dt: Long,
    @SerializedName("main") var main: Main,
    @SerializedName("weather") var weather: List<Weather>,
    @SerializedName("clouds") var clouds: Clouds,
    @SerializedName("wind") var wind: Wind
)
