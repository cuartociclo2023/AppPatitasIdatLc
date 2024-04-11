package pe.edu.idat.apppatitasidatlc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.apppatitasidatlc.repository.MascotaRepository
import pe.edu.idat.apppatitasidatlc.retrofit.request.VoluntarioRequest
import pe.edu.idat.apppatitasidatlc.retrofit.response.MascotaResponse
import pe.edu.idat.apppatitasidatlc.retrofit.response.RegistroResponse

class MascotaViewModel: ViewModel() {

    private var repository = MascotaRepository()
    var registroResponse: LiveData<RegistroResponse>
             = repository.registroResponse

    fun listarMascotas(): LiveData<List<MascotaResponse>>{
        return repository.mascotasPerdidas()
    }

    fun registrarVoluntario(idPersona: Int){
        registroResponse = repository.registroVoluntario(VoluntarioRequest(idPersona))
    }


}