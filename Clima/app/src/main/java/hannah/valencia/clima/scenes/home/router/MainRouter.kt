package hannah.valencia.clima.scenes.home.router

import android.content.Context
import android.content.Intent
import hannah.valencia.clima.scenes.base.BaseActivity
import hannah.valencia.clima.scenes.home.view.HomeActivity
import hannah.valencia.clima.scenes.home.viewModel.HomeViewModel


class MainRouter(val context: Context, val activity: BaseActivity) {
    private val TAG = this::class.java.simpleName

    //Navega al home de la app usando params
    fun routeToHomeView(name: String){
        //intent es mensajería entre apps navegamos entre activities
        val intent= Intent(this.context, HomeActivity::class.java)

        //PutExtra - envio de parámetros
        intent.putExtra(HomeViewModel.PARAM_NAME, name)

        //start activity incia tu intent
        this.context.startActivity(intent)

        //finaliza la actividad actual
        this.activity.finish()
        // sin eso
    }

}