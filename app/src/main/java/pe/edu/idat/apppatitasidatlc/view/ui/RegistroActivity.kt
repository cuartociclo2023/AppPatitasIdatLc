package pe.edu.idat.apppatitasidatlc.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.apppatitasidatlc.R
import pe.edu.idat.apppatitasidatlc.databinding.ActivityRegistroBinding
import pe.edu.idat.apppatitasidatlc.retrofit.response.RegistroResponse
import pe.edu.idat.apppatitasidatlc.util.AppMensaje
import pe.edu.idat.apppatitasidatlc.util.TipoMensaje
import pe.edu.idat.apppatitasidatlc.viewmodel.AuthViewModel

class RegistroActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.btnregresarlogin.setOnClickListener(this)
        binding.btnguardarregistro.setOnClickListener(this)
        authViewModel.registroResponse.observe(this, Observer {
            response -> obtenerDatosRegistro(response)
        })
    }

    private fun obtenerDatosRegistro(response: RegistroResponse) {
        binding.btnregresarlogin.isEnabled = true
        binding.btnguardarregistro.isEnabled = true
        AppMensaje.enviarMensaje(binding.root, response.mensaje, TipoMensaje.ADVERTENCIA)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnregresarlogin -> startActivity(Intent(applicationContext, MainActivity::class.java))
            R.id.btnguardarregistro -> registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        binding.btnregresarlogin.isEnabled = false
        binding.btnguardarregistro.isEnabled = false
        authViewModel.registro(binding.etnombreregistro.text.toString(),
            binding.etapellidoregistro.text.toString(),
            binding.etemailregistro.text.toString(),
            binding.etcelularregistro.text.toString(),
            binding.etusuarioregistro.text.toString(),
            binding.etpasswordregistro.text.toString())
    }
}