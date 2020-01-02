package ralph.stoffers.weatherapp.model.api

import com.google.gson.annotations.SerializedName

data class Wind (
    @SerializedName("speed") var speed: Double,
    @SerializedName("deg") var deg: Int
)
