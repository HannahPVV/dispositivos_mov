package hannah.valencia.clima.scenes.register.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hannah.valencia.clima.scenes.register.model.RegisterModel
import hannah.valencia.clima.scenes.register.view.RegisterActivity

class RegisterViewModel(val context: Context, val activity: RegisterActivity) : ViewModel() {

    private val TAG = RegisterViewModel::class.java.simpleName
    val model = RegisterModel()

    // --- GETTERS PARA LA VISTA ---
    // Exponemos los LiveData del modelo como LiveData (inmutable para la vista)
    val isRegisterEnabled: LiveData<Boolean> get() = model.isRegisterEnabled
    val userError: LiveData<String?> get() = model.userError
    val nameError: LiveData<String?> get() = model.nameError
    val lastError: LiveData<String?> get() = model.lastError
    val passError: LiveData<String?> get() = model.passError
    val confirmError: LiveData<String?> get() = model.confirmError

    /**
     * Función para actualizar los valores del modelo desde la Activity
     */
    fun updateData(user: String, name: String, last: String, pass: String, confirmpass: String) {
        model.user.value = user
        model.name.value = name
        model.last.value = last
        model.pass.value = pass
        model.confirm.value = confirmpass
        validateForm()
    }

    /**
     * Lógica de validación (Basada en tus reglas iniciales)
     */
    fun validateForm() {
        val user = model.user.value?.trim() ?: ""
        val name = model.name.value?.trim() ?: ""
        val last = model.last.value?.trim() ?: ""
        val pass = model.pass.value ?: ""
        val confirm = model.confirm.value ?: ""

        var valid = true

        // --- VALIDACIÓN USUARIO ---
        if (user.isEmpty()) {
            model.userError.value = "El usuario es obligatorio"
            valid = false
        } else if (user.length !in 4..20) {
            model.userError.value = "Debe tener entre 4 y 20 caracteres"
            valid = false
        } else if (user.isNotEmpty() && user[0].isDigit()) {
            model.userError.value = "No puede iniciar con número"
            valid = false
        } else if (!user.all { it.isLetterOrDigit() }) {
            model.userError.value = "No se permiten espacios ni caracteres especiales"
            valid = false
        } else {
            model.userError.value = null
        }

        // --- VALIDACIÓN NOMBRE ---
        if (name.isEmpty()) {
            model.nameError.value = "El nombre es obligatorio"
            valid = false
        } else if (name.length < 2) {
            model.nameError.value = "Nombre demasiado corto"
            valid = false
        } else if (name.length > 30) {
            model.nameError.value = "Máximo 30 caracteres"
            valid = false
        } else if (!name.all { it.isLetter() || it.isWhitespace() }) {
            model.nameError.value = "Solo se permiten letras"
            valid = false
        } else {
            model.nameError.value = null
        }

        // --- VALIDACIÓN APELLIDO ---
        if (last.isEmpty()) {
            model.lastError.value = "El apellido es obligatorio"
            valid = false
        } else if (last.length < 2) {
            model.lastError.value = "Apellido demasiado corto"
            valid = false
        } else if (last.length > 30) {
            model.lastError.value = "Máximo 30 caracteres"
            valid = false
        } else if (!last.all { it.isLetter() || it.isWhitespace() }) {
            model.lastError.value = "Solo se permiten letras"
            valid = false
        } else {
            model.lastError.value = null
        }

        // --- VALIDACIÓN CONTRASEÑA ---
        if (pass.isEmpty()) {
            model.passError.value = "La contraseña es obligatoria"
            valid = false
        } else if (pass.length < 8) {
            model.passError.value = "Debe tener al menos 8 caracteres"
            valid = false
        } else if (!pass.any { it.isUpperCase() } || !pass.any { it.isLowerCase() } ||
            !pass.any { it.isDigit() } || pass.contains(" ")) {
            model.passError.value = "Debe incluir mayúsculas, minúsculas y números"
            valid = false
        } else {
            model.passError.value = null
        }

        // --- VALIDACIÓN CONFIRMACIÓN ---
        if (confirm.isEmpty()) {
            model.confirmError.value = "Debes confirmar la contraseña"
            valid = false
        } else if (confirm != pass) {
            model.confirmError.value = "Las contraseñas no coinciden"
            valid = false
        } else {
            model.confirmError.value = null
        }

        model.isRegisterEnabled.value = valid
        Log.i(TAG, "Formulario válido: $valid")
    }

}