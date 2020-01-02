package ralph.stoffers.weatherapp.model.api

import com.google.gson.annotations.SerializedName

data class WeatherApiResponse(
    @SerializedName("page") var coord: Coord,
    @SerializedName("weather") var weather: List<Weather>,
    @SerializedName("base") var base: String,
    @SerializedName("main") var main: Main,
    @SerializedName("visibility") var visibility: Int,
    @SerializedName("wind") var wind: Wind,
    @SerializedName("clouds") var clouds: Clouds,
    @SerializedName("name") var name: String,
    @SerializedName("cod") var cod: Int
)