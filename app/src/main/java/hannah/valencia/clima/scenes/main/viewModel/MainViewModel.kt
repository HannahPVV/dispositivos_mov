package hannah.valencia.clima.scenes.main.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hannah.valencia.clima.dataClases.User
import hannah.valencia.clima.scenes.home.router.MainRouter
import hannah.valencia.clima.scenes.main.model.MainModel
import hannah.valencia.clima.scenes.main.view.MainActivity
import hannah.valencia.clima.sharedPreference.SharedPreferenceConstants
import hannah.valencia.clima.sharedPreference.SharedPreferenceManager

// Llevará la lógica de negocio, en este caso validará el formulario.
//Intermediario entre vista y modelo
class MainViewModel (val context: Context, val activity: MainActivity): ViewModel(){

   //VARIABLES PRIVADAS
    private val TAG = MainViewModel::class.java.simpleName
    private val model = MainModel()

    private val router = MainRouter(context,activity)
    private val sharedPreferenceManager = SharedPreferenceManager(context)

    /**
     * Método que se ejecuta cuando se inicializa View Model
     */

    init{}

    /**
     * Variable que permite obtener el usuario y cambiar su valor
     * Datos se alamcenana en model y se muestran por viewmodel
     */

    var user: User
        // contiene al usuario de Model
        get() = this.model.user

        // cambia el usario de model
        set(value){
            this.model.user= value
        }

    val isValidForm: LiveData<Boolean>
        get()=this.model.isValidForm
    // --- OBSERVERS ÚNICOS DE USUARIO Y CONTRASEÑA ---
    // Se exponen como LiveData para que la MainActivity pueda reaccionar a cambios individuales

    val userName: LiveData<String>
        get() = this.model.userName

    val password: LiveData<String>
        get() = this.model.password

    val isValidUser: LiveData<String?>
        get() = this.model.isValidUser

    // Permite que la vista sepa si la contraseña es válida (Boolean)
    val isValidPassword: LiveData<Boolean>
        get() = this.model.isValidPassword

    /**
     * Funcion para validar la infromación ingresada por usuario
     * Si son correctos, entoces cambia el estado a válido
     */

    fun validateForm() {
        Log.i(TAG, "User: ${this.user}")

        // TODO: Valida que tu observer de usuario sea STRING y no BOOLEAN
        // Si el campo no está vacío, quitamos el error; de lo contrario, pedimos que sea requerido
        if (this.user.userName.isNotEmpty()) {
            this.model.isValidUser.value = null
        } else {
            this.model.isValidUser.value = "El usuario es requerido"
        }

        // Observers únicos para monitorear campos específicos
        this.model.isValidPassword.value = this.user.password.isNotEmpty()

        // El formulario es válido solo si AMBOS campos tienen contenido
        this.model.isValidForm.value = this.user.userName.isNotEmpty() && this.user.password.isNotEmpty()

        Log.i(TAG, "isValidForm: ${this.model.isValidForm.value}")
        Log.i(TAG, "isValidUser: ${this.model.isValidUser.value}")
        Log.i(TAG, "isValidPassword: ${this.model.isValidPassword.value}")
    }

    /**
     *Funcion para iniciar sesión
     */
    fun validateLogin() {
        // Verificamos si hay alguien registrado
        if (this.sharedPreferenceManager.getBoolean(SharedPreferenceConstants.IS_REGISTERED_KEY)) {
            this.model.isValidUser.value = null // Corregido el nombre de la variable

            // CON GET RECUPERAMOS LOS DATOS
            val userGuardado = this.sharedPreferenceManager.getString(SharedPreferenceConstants.USER_KEY)
            val passGuardada = this.sharedPreferenceManager.getString(SharedPreferenceConstants.PASSWORD_KEY)
            val nombreGuardado = this.sharedPreferenceManager.getString(SharedPreferenceConstants.USER_NAME_KEY)

            //  VALIDACIONES DE USUARIO Y CONTRASEÑA
            if (this.user.userName == userGuardado && this.user.password == passGuardada) {
                // Si coinciden, navegamos al Home usando el nombre real guardado
                this.router.routeToHomeView(name = nombreGuardado ?: "Usuario")
            } else {
                this.model.isValidUser.value = "Credenciales incorrectas"
            }
        } else {
            this.model.isValidUser.value = "El usuario no existe"
        }
    }


}