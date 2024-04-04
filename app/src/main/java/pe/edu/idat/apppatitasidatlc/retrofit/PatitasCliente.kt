package pe.edu.idat.apppatitasidatlc.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object PatitasCliente {

    private var retrofitCliente = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(15, TimeUnit.MINUTES)
        .writeTimeout(15, TimeUnit.MINUTES)
        //.addInterceptor(ApiInterceptor())
        .build()

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("http://www.kreapps.biz/patitas/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(retrofitCliente)
        .build()

    val retrofitService: PatitasServicio by lazy {
        buildRetrofit().create(PatitasServicio::class.java)
    }
}