package hannah.valencia.clima.scenes.explore.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hannah.valencia.clima.scenes.explore.model.LocationModel
import hannah.valencia.clima.scenes.explore.model.WeatherType

class ExploreViewModel : ViewModel() {

    private val _locations = MutableLiveData<List<LocationModel>>()
    val locations: LiveData<List<LocationModel>> = _locations

    init {
        _locations.value = listOf(
            LocationModel("Zibatá", 23, WeatherType.CLOUDY),
            LocationModel("Guadalajara", 20, WeatherType.RAINY),
            LocationModel("CDMX", 23, WeatherType.SUNNY),
            LocationModel("Londres", 2, WeatherType.SNOWY)
        )
    }

    fun addLocation(city: String) {
        val currentList = _locations.value ?: emptyList()

        val weather = WeatherType.values().random()

        val temperature = when (weather) {
            WeatherType.SNOWY -> (-5..5).random()
            WeatherType.RAINY -> (8..18).random()
            WeatherType.CLOUDY -> (10..22).random()
            WeatherType.SUNNY -> (20..35).random()
        }

        val newLocation = LocationModel(
            city = city,
            temperature = temperature,
            weather = weather
        )

        _locations.value = currentList + newLocation
    }
}