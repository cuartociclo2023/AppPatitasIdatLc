package pe.edu.idat.apppatitasidatlc.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.apppatitasidatlc.R
import pe.edu.idat.apppatitasidatlc.databinding.ActivityMainBinding
import pe.edu.idat.apppatitasidatlc.model.db.entity.PersonaEntity
import pe.edu.idat.apppatitasidatlc.retrofit.response.LoginResponse
import pe.edu.idat.apppatitasidatlc.util.AppMensaje
import pe.edu.idat.apppatitasidatlc.util.SharedPreferencesManager
import pe.edu.idat.apppatitasidatlc.util.TipoMensaje
import pe.edu.idat.apppatitasidatlc.viewmodel.AuthViewModel
import pe.edu.idat.apppatitasidatlc.viewmodel.PersonaViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var personaViewModel: PersonaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        personaViewModel = ViewModelProvider(this)
            .get(PersonaViewModel::class.java)
        binding.btnlogin.setOnClickListener(this)
        binding.btnregistro.setOnClickListener(this)
        binding.cbguardarusuario.setOnClickListener(this)
        authViewModel.loginResponse.observe(this, Observer {
            response -> obtenerDatosLogin(response)
        })

        if(recordarDatosLogin()){
            binding.cbguardarusuario.isChecked = true
            binding.etusuario.isEnabled = false
            binding.etpassword.isEnabled = false
            binding.cbguardarusuario.text = "Quitar check para ingresar con otro usuario"
            personaViewModel.obtener()
                .observe(this, Observer { persona ->
                    persona?.let {
                        binding.etusuario.setText(persona.usuario)
                        binding.etpassword.setText(persona.password)
                    }
                })
        }else{
            personaViewModel.eliminar()
        }
    }

    private fun obtenerDatosLogin(response: LoginResponse) {
        if(response.rpta){
            val personaEntity = PersonaEntity(
                response.idpersona.toInt(), response.nombres, response.apellidos, response.email,
                response.celular, response.esvoluntario, response.password, response.usuario)
            if(recordarDatosLogin()){
                personaViewModel.actualizar(personaEntity)
            }else{
                personaViewModel.insertar(personaEntity)
                if(binding.cbguardarusuario.isChecked){
                    SharedPreferencesManager().setValorBoolean("PREF_RECORDAR",
                        true)
                }
            }

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
            R.id.cbguardarusuario -> setearValoresRecordar(v)
        }

    }
    private fun autenticarUsuario() {
        binding.btnlogin.isEnabled = false
        binding.btnregistro.isEnabled = false
        authViewModel.login(binding.etusuario.text.toString(),
            binding.etpassword.text.toString())
    }
    private fun recordarDatosLogin(): Boolean{
        return SharedPreferencesManager().getValorBoolean("PREF_RECORDAR")
    }

    private fun setearValoresRecordar(vista: View) {
        if(vista is CheckBox){
            val checkeo = vista.isChecked
            if(!checkeo){
                if(recordarDatosLogin()){
                    SharedPreferencesManager().eliminarPreferencia("PREF_RECORDAR")
                    personaViewModel.eliminar()
                    binding.etusuario.isEnabled = true
                    binding.etpassword.isEnabled = true
                    binding.cbguardarusuario.text = getString(R.string.valcbguardar)
                }
            }
        }
    }
}