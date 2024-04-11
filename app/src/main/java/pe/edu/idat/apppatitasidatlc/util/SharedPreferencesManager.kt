package pe.edu.idat.apppatitasidatlc.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager() {
    private val APP_SETTINGS_FILE = "APP_SETTINGS"
    private fun getSharedPreferences():SharedPreferences{
        return MiApp.applicationContext.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE)
    }
    fun setValorBoolean(nombre:String, valor:Boolean){
        val editor = getSharedPreferences().edit()
        editor.putBoolean(nombre, valor)
        editor.commit()
    }
    fun getValorBoolean(nombre:String): Boolean{
        return getSharedPreferences().getBoolean(nombre, false)
    }
    fun eliminarPreferencia(nombre:String){
        getSharedPreferences().edit().remove(nombre).apply()
    }
}