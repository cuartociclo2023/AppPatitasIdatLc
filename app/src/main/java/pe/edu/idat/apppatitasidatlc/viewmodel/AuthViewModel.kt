package pe.edu.idat.apppatitasidatlc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.apppatitasidatlc.repository.AuthRepository
import pe.edu.idat.apppatitasidatlc.retrofit.request.LoginRequest
import pe.edu.idat.apppatitasidatlc.retrofit.request.RegistroRequest
import pe.edu.idat.apppatitasidatlc.retrofit.response.LoginResponse
import pe.edu.idat.apppatitasidatlc.retrofit.response.RegistroResponse

class AuthViewModel : ViewModel() {
    var loginResponse: LiveData<LoginResponse>
    var registroResponse: LiveData<RegistroResponse>
    private var repository = AuthRepository()
    init {
        loginResponse = repository.loginResponse
        registroResponse = repository.registroResponse
    }
    fun login(usuario:String, password:String){
        loginResponse = repository.login(LoginRequest(usuario, password))
    }
    fun registro(nombres:String, apellidos:String,
                 email:String, celular:String,
                 usuario:String,password: String){
        registroResponse = repository.registro(RegistroRequest(nombres,apellidos,email,
            celular, password, usuario))
    }
}