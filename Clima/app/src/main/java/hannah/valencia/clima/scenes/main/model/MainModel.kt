package hannah.valencia.clima.scenes.main.model

import androidx.lifecycle.MutableLiveData
import hannah.valencia.clima.dataClases.User

// Tiene el estado del formulario y los datos del usuario,
// para ello se usa MutableLiveData para exponer si el formulario es válido

data class MainModel(
    var user: User = User(),
    val isValidForm: MutableLiveData<Boolean> = MutableLiveData<Boolean>(),

    // Observers únicos para monitorear campos específicos en tiempo real
    val userName: MutableLiveData<String> = MutableLiveData<String>(),
    val password: MutableLiveData<String> = MutableLiveData<String>(),

    val isValidUser: MutableLiveData<String?> = MutableLiveData<String?>(null),
    val isValidPassword: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
)
