package hannah.valencia.clima.scenes.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {
    private val TAG =  this:: class.java.simpleName // cambiamos por el this B

    //Se activa cuando se crea la act por 1era vez, cuando se crea la act esta en estado Created

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    //Hace actividad visible al usuario, preparación de act en 1er plano e interactiva
    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart")
    }

    //App interactúa con usuario, permanece en este estado hasta que se interrumpe (ej:llamadacel)
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume")
    }

    //Indica actividad no esta en 1er plano (interrupción), no siempre signfica que se esta destruyendo
    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause")
    }

    //Actividad no visible al usuario: act recien lanzada o act termina ejecucción y esta pot terminar
    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop")
    }

    //Se llama antes de que la actividad sea destruida por completo.
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    // Se activa solo si la actividad vuelve a la pantalla después de haber estado en Stop.
    override fun onRestart(){
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

}