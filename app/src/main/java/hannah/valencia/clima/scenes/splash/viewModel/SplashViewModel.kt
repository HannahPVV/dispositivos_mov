package hannah.valencia.clima.scenes.splash.viewModel

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.lifecycle.ViewModel
import hannah.valencia.clima.scenes.splash.router.SplashRouter
import hannah.valencia.clima.scenes.splash.view.SplashActivity

class SplashViewModel (context: Context, activity: SplashActivity): ViewModel(){
    private val TAG = SplashViewModel::class.java.simpleName
    private var router = SplashRouter(context,activity)

    //Metodo que se ejecuta para inicializar ViewMOdel
    init {
        countdown()
    }

    //cuenta atras
    private fun countdown(){
        Log.i(TAG, "Inicia cuenta atrás")
        Handler().postDelayed({
            Log.i(TAG,"Navegamos al login")
            this.router.routeToMainView()
        }, 2500)
    }


}