package pe.edu.idat.apppatitasidatlc.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import pe.edu.idat.apppatitasidatlc.R
import pe.edu.idat.apppatitasidatlc.databinding.FragmentVoluntarioBinding
import pe.edu.idat.apppatitasidatlc.model.db.entity.PersonaEntity
import pe.edu.idat.apppatitasidatlc.retrofit.response.RegistroResponse
import pe.edu.idat.apppatitasidatlc.util.AppMensaje
import pe.edu.idat.apppatitasidatlc.util.TipoMensaje
import pe.edu.idat.apppatitasidatlc.viewmodel.MascotaViewModel
import pe.edu.idat.apppatitasidatlc.viewmodel.PersonaViewModel


class VoluntarioFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentVoluntarioBinding? = null
    private val binding get() = _binding!!
    private lateinit var mascotaViewModel: MascotaViewModel
    private lateinit var personaViewModel: PersonaViewModel
    private lateinit var personaEntity: PersonaEntity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVoluntarioBinding.inflate(inflater, container, false)
        mascotaViewModel = ViewModelProvider(requireActivity())
            .get(MascotaViewModel::class.java)
        personaViewModel = ViewModelProvider(requireActivity())
            .get(PersonaViewModel::class.java)
        personaViewModel.obtener()
            .observe(viewLifecycleOwner, Observer {
                    persona ->
                persona?.let {
                    if(persona.esvoluntario == "1"){
                        formVoluntario()
                    }else{
                        personaEntity = persona
                    }
                }
            })
        binding.btnregistrarvoluntario.setOnClickListener(this)
        mascotaViewModel.registroResponse.observe(viewLifecycleOwner,
            Observer {
                respuestaRegistroVoluntario(it)
            })
        return binding.root
    }

    private fun respuestaRegistroVoluntario(it: RegistroResponse) {
        if(it.rpta){
            val nuevaPersonaEntity = PersonaEntity(
                personaEntity.id, personaEntity.nombres, personaEntity.apellidos,
                personaEntity.email, personaEntity.celular, "1",  personaEntity.password,
                personaEntity.usuario
            )
            personaViewModel.actualizar(nuevaPersonaEntity)
            formVoluntario()
        }
        AppMensaje.enviarMensaje(binding.root, it.mensaje, TipoMensaje.CORRECTO)
        binding.btnregistrarvoluntario.isEnabled = true
    }

    override fun onClick(v: View?) {
        if(binding.cbaceptarterminos.isChecked){
            binding.btnregistrarvoluntario.isEnabled = false
            mascotaViewModel.registrarVoluntario(personaEntity.id)
        }else{
            AppMensaje.enviarMensaje(binding.root,
                "Acepte los términos y condiciones para ser voluntario",
                TipoMensaje.ERROR)
        }
    }
    private fun formVoluntario(){
        binding.cbaceptarterminos.visibility = View.GONE
        binding.btnregistrarvoluntario.visibility = View.GONE
        binding.textView4.visibility = View.GONE
        binding.textView3.text = "Gracias por su compromiso como voluntario"
    }

}