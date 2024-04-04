package pe.edu.idat.apppatitasidatlc.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.idat.apppatitasidatlc.retrofit.PatitasCliente
import pe.edu.idat.apppatitasidatlc.retrofit.request.RegistroRequest
import pe.edu.idat.apppatitasidatlc.retrofit.request.VoluntarioRequest
import pe.edu.idat.apppatitasidatlc.retrofit.response.MascotaResponse
import pe.edu.idat.apppatitasidatlc.retrofit.response.RegistroResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MascotaRepository {

    var mascotaResponse = MutableLiveData<List<MascotaResponse>>()
    var registroResponse = MutableLiveData<RegistroResponse>()

    fun mascotasPerdidas(): MutableLiveData<List<MascotaResponse>>{
        val call : Call<List<MascotaResponse>> = PatitasCliente.retrofitService.mascotas()
        call.enqueue(object : Callback<List<MascotaResponse>>{
            override fun onResponse(
                call: Call<List<MascotaResponse>>,
                response: Response<List<MascotaResponse>>
            ) {
               mascotaResponse.value = response.body()
            }
            override fun onFailure(call: Call<List<MascotaResponse>>, t: Throwable) {
                Log.i("ErrorListMascota", t.message.toString())
            }
        })
        return mascotaResponse
    }
    fun registroVoluntario(voluntarioRequest: VoluntarioRequest): MutableLiveData<RegistroResponse>{
        val call: Call<RegistroResponse> = PatitasCliente.retrofitService.registrarVoluntario(voluntarioRequest)
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