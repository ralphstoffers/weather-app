package ralph.stoffers.weatherapp.model.api

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("dt") var dt: Int,
    @SerializedName("main") var main: Main,
    @SerializedName("weather") var weather: Weather,
    @SerializedName("clouds") var clouds: Clouds,
    @SerializedName("wind") var wind: Wind
)
