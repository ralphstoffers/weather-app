package ralph.stoffers.weatherapp.model.api

import com.google.gson.annotations.SerializedName

data class ForecastApiResponse(
    @SerializedName("cod") var cod: Int,
    @SerializedName("city") var city: City,
    @SerializedName("list") var list: List<Forecast>
) {
    override fun toString(): String {
        return "ForecastApiResponse(cod=$cod, city=$city, list=$list)"
    }
}