package hannah.valencia.clima.scenes.home.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hannah.valencia.clima.scenes.base.BaseActivity
import hannah.valencia.clima.scenes.home.model.HomeModel


class HomeViewModel (val context: Context, val activity: BaseActivity): ViewModel(){
    //VARIABLES PRIVADAS
    private val TAG = this::class.java.simpleName
    private val model = HomeModel()

    companion object{
        val PARAM_NAME= "PARAM_NAME"
        fun hello()= print("Hello World")
    }

    /**
     * Función que se ejecuta cuando se incializa ViewModel
     */

    init {
        this.model.name.value=(this.activity.intent.getSerializableExtra(PARAM_NAME) as String) ?:""
    }

    val name: LiveData<String>
        get() = this.model.name


}