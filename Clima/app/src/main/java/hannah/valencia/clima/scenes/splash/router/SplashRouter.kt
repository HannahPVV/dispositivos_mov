package hannah.valencia.clima.scenes.splash.router

import android.content.Context
import android.content.Intent
import hannah.valencia.clima.scenes.main.view.MainActivity
import hannah.valencia.clima.scenes.splash.view.SplashActivity

class SplashRouter(val context: Context, val activity: SplashActivity) {
    private val TAG = SplashRouter::class.java.simpleName

    fun routeToMainView(){
        this.context.startActivity(Intent(this.context, MainActivity::class.java))

        this.activity.finish()
    }
}