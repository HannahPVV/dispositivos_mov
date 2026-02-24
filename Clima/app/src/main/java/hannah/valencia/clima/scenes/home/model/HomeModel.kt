package hannah.valencia.clima.scenes.home.model
import androidx.lifecycle.MutableLiveData

class HomeModel {
    val name: MutableLiveData<String> = MutableLiveData<String>()
    val forecastList = MutableLiveData<List<ForecastModel>>()
    val humidity = MutableLiveData<String>()
    val wind = MutableLiveData<String>()
    val uvIndex = MutableLiveData<String>()
    val feelsLike = MutableLiveData<String>()
    val precipitation = MutableLiveData<String>()
}