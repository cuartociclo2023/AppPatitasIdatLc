package pe.edu.idat.apppatitasidatlc.retrofit

import pe.edu.idat.apppatitasidatlc.retrofit.request.LoginRequest
import pe.edu.idat.apppatitasidatlc.retrofit.request.RegistroRequest
import pe.edu.idat.apppatitasidatlc.retrofit.request.VoluntarioRequest
import pe.edu.idat.apppatitasidatlc.retrofit.response.LoginResponse
import pe.edu.idat.apppatitasidatlc.retrofit.response.MascotaResponse
import pe.edu.idat.apppatitasidatlc.retrofit.response.RegistroResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface PatitasServicio {

    @POST("login.php")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @PUT("persona.php")
    fun registrar(@Body registroRequest: RegistroRequest): Call<RegistroResponse>

    @POST("personavoluntaria.php")
    fun registrarVoluntario(@Body voluntarioRequest: VoluntarioRequest): Call<RegistroResponse>

    @GET("mascotaperdida.php")
    fun mascotas(): Call<List<MascotaResponse>>

}