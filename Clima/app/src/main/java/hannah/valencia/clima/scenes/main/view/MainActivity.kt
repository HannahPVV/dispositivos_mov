package hannah.valencia.clima.scenes.main.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import hannah.valencia.clima.scenes.base.BaseActivity
import hannah.valencia.clima.databinding.ActivityMainBinding
import hannah.valencia.clima.scenes.main.viewModel.MainViewModel
import hannah.valencia.clima.scenes.register.view.RegisterActivity
import hannah.valencia.clima.sharedPreference.SharedPreferenceConstants
import hannah.valencia.clima.sharedPreference.SharedPreferenceManager

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferenceManager: SharedPreferenceManager

    // ViewModel: contiene la lógica de presentación y el estado del formulario
    private lateinit var viewModel: MainViewModel

    private var TAG = MainActivity::class.java.simpleName


    /**
     * On create
     * // Para crear una actividad por 1era vez
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        this.configureActivity()
    }

    /**
     * Configuramos nuestro activity
     * Incializa vista y view model, se configura listeners (event usaurio), define estado
     * incial componentes, y se configuran observers para reaccionar a cambios del View MODEL
      */

    private fun configureActivity(){
        this.initActivityView()
        this.configureListeners()
        this.initComponents()
        this.setObservers()

    }

    /**
     * initActivityView:
     * - Infla el layout usando ViewBinding para convertir el XML en objetos.
     * - Asigna la vista como contenido de la Activity.
     * - Inicializa el ViewModel, lo que provoca la ejecución de su bloque init{}.
     */
    private fun initActivityView(){
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root) // Esto es lo que "infla" el XML
        this.sharedPreferenceManager = SharedPreferenceManager(this)

        this.viewModel = MainViewModel(this, this )


        val nombreGuardado = this.sharedPreferenceManager.getString(SharedPreferenceConstants.USER_NAME_KEY)
        this.binding.tvHello.text = "Bienvenido $nombreGuardado!"
    }

    // Estado inical interfaz:
    // El botón esta deshabilitado, hasta que el formulario sea válido
    private fun initComponents(){
        this.binding.btnlogin.isEnabled = false
    }


    /**
     * La vista observa estados del ViewModel y  actualiza la interfaz.
     * Aquí se observa isValidForm para: Habilitar o deshabilitar el botón, o
     * mostrar mensajes de error en los inputs cuando el formulario no es válido
     */
    private fun setObservers(){
        //Observar cambios en el usuario y actualizar la interfaz
        this.viewModel.isValidForm.observe(this, Observer { isValid ->
            this.binding.btnlogin.isEnabled = isValid

            this.binding.etUsername.error = if (isValid) null else "El usuario es requerido"
            this.binding.etPassword.error = if (isValid) null else "La contraseña es requerida"
        })

        // TODO: Agregar observers únicos de usuario y contraseña

        // 2. Observamos el mensaje de error que viene del ViewModel (isValidUser)
        // Aquí el ViewModel ya decidió si el texto es "El usuario es requerido" o null
        this.viewModel.isValidUser.observe(this) { mensaje ->
            this.binding.etUsername.error = mensaje
        }

        // 3. Observamos la validación de la contraseña (isValidPassword)
        this.viewModel.isValidPassword.observe(this) { esValido ->
            // Si no es válido, mostramos el mensaje; si es válido, quitamos el error
            this.binding.etPassword.error = if (esValido) null else "La contraseña es requerida"
        }



    }

    // La vista escucha acciones del usuario (clicks y escritura) y envía esos cambios al ViewModel.
    private fun configureListeners(){
        this.binding.btnlogin.setOnClickListener {
            this.viewModel.validateLogin()
        }
        this.binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        // Cada vez que cambia el texto, se actualiza el modelo en el ViewModel y se revalida el formulario
        this.binding.etUsername.addTextChangedListener {
            this.viewModel.user.userName =it.toString()
            this.viewModel.validateForm()
        }
        this.binding.etPassword.addTextChangedListener {
            this.viewModel.user.password= it.toString()
            this.viewModel.validateForm()
        }
        this.binding.btnBiometric.setOnClickListener {
            this.viewModel.useBiometrics()
        }

    }





}