package pe.edu.idat.apppatitasidatlc.util

import android.app.Application

class MiApp : Application() {

    companion object{
        lateinit var instancia: MiApp
    }

    override fun onCreate() {
        super.onCreate()
        instancia = this
    }
}