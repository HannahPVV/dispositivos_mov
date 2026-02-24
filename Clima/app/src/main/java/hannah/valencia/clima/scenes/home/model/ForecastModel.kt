package hannah.valencia.clima.scenes.home.model

data class ForecastModel(
    val hour: String,
    val temp: Int,
    val iconRes: Int //id drawable
)