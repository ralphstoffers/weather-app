package ralph.stoffers.weatherapp.model.api

import com.google.gson.annotations.SerializedName

data class Clouds (
    @SerializedName("all") var all: Int
)
