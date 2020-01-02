package ralph.stoffers.weatherapp.model.entity

data class CurrentWeather(
    val name: String,
    val temp: Double,
    val description: String,
    val wind: String,
    val icon: String
) {
    override fun toString(): String {
        return "CurrentWeather(name='$name', temp=$temp, description='$description', wind='$wind', icon='$icon')"
    }
}