package hannah.valencia.clima.scenes.register.model

import androidx.lifecycle.MutableLiveData

class RegisterModel{
    val user = MutableLiveData<String>("")
    val name = MutableLiveData<String>("")
    val last = MutableLiveData<String>("")
    val pass = MutableLiveData<String>("")
    val confirm = MutableLiveData<String>("")

    // Estados de validación y errores
    val isRegisterEnabled = MutableLiveData<Boolean>(false)
    val userError = MutableLiveData<String?>(null)
    val nameError = MutableLiveData<String?>(null)
    val lastError = MutableLiveData<String?>(null)
    val passError = MutableLiveData<String?>(null)
    val confirmError = MutableLiveData<String?>(null)
}