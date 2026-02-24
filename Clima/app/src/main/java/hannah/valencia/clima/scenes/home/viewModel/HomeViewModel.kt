package hannah.valencia.clima.scenes.home.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hannah.valencia.clima.R
import hannah.valencia.clima.scenes.base.BaseActivity
import hannah.valencia.clima.scenes.home.model.ForecastModel
import hannah.valencia.clima.scenes.home.model.HomeModel




class HomeViewModel (val context: Context, val activity: BaseActivity): ViewModel(){
    private val TAG = this::class.java.simpleName
    private val model = HomeModel()



    init {
        // Cargamos los datos de la lista y el clima aquí mismo
        loadTestData()
    }

    // Exponemos los datos para el Fragment
    val name: LiveData<String> get() = this.model.name
    val forecastList: LiveData<List<ForecastModel>> get() = this.model.forecastList
    val humidity: LiveData<String> get() = this.model.humidity
    val wind: LiveData<String> get() = this.model.wind

    val uvIndex: LiveData<String> get() = this.model.uvIndex

    val precipitation: LiveData<String> get() = this.model.precipitation

    val feelsLike: LiveData<String> get() = this.model.feelsLike


    private fun loadTestData() {
        this.model.forecastList.value = listOf(
            ForecastModel("Ahora", 25, R.drawable.ic_viento),
            ForecastModel("1 PM", 26, R.drawable.ic_viento),
            ForecastModel("2 PM", 27, R.drawable.ic_viento),
        )
        this.model.humidity.value = "12 %"
        this.model.wind.value = "13 km/h"
        this.model.uvIndex.value = "8"
        this.model.precipitation.value = "0 mm"
        this.model.feelsLike.value = "26°"
        this.model.name.value = "Londres"


    }
}