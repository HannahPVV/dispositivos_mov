package hannah.valencia.clima.scenes.register.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import hannah.valencia.clima.databinding.ActivityRegisterBinding
import hannah.valencia.clima.scenes.base.BaseActivity
import hannah.valencia.clima.scenes.register.viewModel.RegisterViewModel
import hannah.valencia.clima.sharedPreference.SharedPreferenceConstants
import hannah.valencia.clima.sharedPreference.SharedPreferenceManager

class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var sharedPreferenceManager: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        this.initActivityView()
        this.setupObservers()
        this.setupListeners()
    }

    private fun initActivityView() {
        this.binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.viewModel = RegisterViewModel(this, this)


        this.sharedPreferenceManager = SharedPreferenceManager(this)



        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupObservers() {
        // La vista REACCIONA a los cambios del modelo a través del ViewModel
        viewModel.isRegisterEnabled.observe(this) { enabled ->
            binding.btnRegister.isEnabled = enabled
        }

        // Observamos errores específicos para mostrarlos en los campos
        viewModel.userError.observe(this) { binding.etUser.error = it }
        viewModel.nameError.observe(this) { binding.etName.error = it }
        viewModel.lastError.observe(this) { binding.etLast.error = it }
        viewModel.passError.observe(this) { binding.etPass.error = it }
        viewModel.confirmError.observe(this) { binding.etConfirm.error = it }
    }

    private fun setupListeners() {
        // Función que notifica al ViewModel cada vez que hay un cambio
        val watcher = {
            viewModel.updateData(
                binding.etUser.text.toString(),
                binding.etName.text.toString(),
                binding.etLast.text.toString(),
                binding.etPass.text.toString(),
                binding.etConfirm.text.toString()
            )
        }

        // Asignamos el listener a cada campo para validación en tiempo real
        binding.etUser.addTextChangedListener { watcher() }
        binding.etName.addTextChangedListener { watcher() }
        binding.etLast.addTextChangedListener { watcher() }
        binding.etPass.addTextChangedListener { watcher() }
        binding.etConfirm.addTextChangedListener { watcher() }

        //btn back
        binding.btnBack.setOnClickListener {
            this.finish() // cierra la actividad actual y te regresa al Login
        }

        // Acción al presionar el botón de registro
        binding.btnRegister.setOnClickListener {
            val user = binding.etUser.text.toString()
            val name = binding.etName.text.toString()
            val last = binding.etLast.text.toString()
            val pass = binding.etPass.text.toString()
            val confirm = binding.etConfirm.text.toString()


            this.sharedPreferenceManager.setString(SharedPreferenceConstants.USER_KEY, user)
            this.sharedPreferenceManager.setString(SharedPreferenceConstants.USER_NAME_KEY, name)
            this.sharedPreferenceManager.setString(SharedPreferenceConstants.USER_LASTNAME_KEY, last)
            this.sharedPreferenceManager.setString(SharedPreferenceConstants.PASSWORD_KEY,confirm)
            this.sharedPreferenceManager.setBoolean(SharedPreferenceConstants.IS_REGISTERED_KEY, true)

            // Snackbar de éxito con acción para finalizar la actividad
            Snackbar.make(binding.main, "Registro exitoso", Snackbar.LENGTH_INDEFINITE)
                .setAction("Iniciar Sesión") {
                    this.finish() // Regresa a la pantalla anterior
                }
                .show()



        }
    }
}
