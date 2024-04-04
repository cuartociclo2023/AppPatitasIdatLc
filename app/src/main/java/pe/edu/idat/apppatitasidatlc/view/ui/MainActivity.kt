package pe.edu.idat.apppatitasidatlc.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.apppatitasidatlc.R
import pe.edu.idat.apppatitasidatlc.databinding.ActivityMainBinding
import pe.edu.idat.apppatitasidatlc.retrofit.response.LoginResponse
import pe.edu.idat.apppatitasidatlc.util.AppMensaje
import pe.edu.idat.apppatitasidatlc.util.TipoMensaje
import pe.edu.idat.apppatitasidatlc.viewmodel.AuthViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        binding.btnlogin.setOnClickListener(this)
        binding.btnregistro.setOnClickListener(this)
        authViewModel.loginResponse.observe(this, Observer {
            response -> obtenerDatosLogin(response)
        })
    }

    private fun obtenerDatosLogin(response: LoginResponse) {
        if(response.rpta){
            startActivity(Intent(applicationContext, HomeActivity::class.java))
        }else{
            AppMensaje.enviarMensaje(binding.root, response.mensaje, TipoMensaje.ERROR)
        }
        binding.btnlogin.isEnabled = true
        binding.btnregistro.isEnabled = true
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnlogin -> autenticarUsuario()
            R.id.btnregistro -> startActivity(Intent(applicationContext, RegistroActivity::class.java))
        }

    }
    private fun autenticarUsuario() {
        binding.btnlogin.isEnabled = false
        binding.btnregistro.isEnabled = false
        authViewModel.login(binding.etusuario.text.toString(),
            binding.etpassword.text.toString())
    }
}