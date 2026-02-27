package hannah.valencia.clima.scenes.explore.model

data class LocationModel(
    val city: String,
    val temperature: Int,
    val weather: WeatherType
)

enum class WeatherType {
    SUNNY, CLOUDY, RAINY, SNOWY
}