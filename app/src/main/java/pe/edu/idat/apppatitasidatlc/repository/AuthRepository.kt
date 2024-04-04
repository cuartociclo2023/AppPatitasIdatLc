package pe.edu.idat.apppatitasidatlc.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.idat.apppatitasidatlc.retrofit.PatitasCliente
import pe.edu.idat.apppatitasidatlc.retrofit.PatitasServicio
import pe.edu.idat.apppatitasidatlc.retrofit.request.LoginRequest
import pe.edu.idat.apppatitasidatlc.retrofit.request.RegistroRequest
import pe.edu.idat.apppatitasidatlc.retrofit.response.LoginResponse
import pe.edu.idat.apppatitasidatlc.retrofit.response.RegistroResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    var loginResponse= MutableLiveData<LoginResponse>()
    var registroResponse = MutableLiveData<RegistroResponse>()

    fun login(loginRequest: LoginRequest): MutableLiveData<LoginResponse>{
        val call: Call<LoginResponse> = PatitasCliente.retrofitService.login(loginRequest)
        call.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginResponse.value = response.body()
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("ErrorLogin", t.message.toString())
            }
        })
        return loginResponse
    }

    fun registro(registroRequest: RegistroRequest): MutableLiveData<RegistroResponse>{
        val call: Call<RegistroResponse> = PatitasCliente.retrofitService.registrar(registroRequest)
        call.enqueue(object : Callback<RegistroResponse>{
            override fun onResponse(call: Call<RegistroResponse>, response: Response<RegistroResponse>) {
                registroResponse.value = response.body()
            }
            override fun onFailure(call: Call<RegistroResponse>, t: Throwable) {
                Log.e("ErrorLogin", t.message.toString())
            }
        })
        return registroResponse
    }
}