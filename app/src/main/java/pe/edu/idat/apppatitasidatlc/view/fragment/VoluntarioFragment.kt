package pe.edu.idat.apppatitasidatlc.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import pe.edu.idat.apppatitasidatlc.R
import pe.edu.idat.apppatitasidatlc.databinding.FragmentVoluntarioBinding
import pe.edu.idat.apppatitasidatlc.viewmodel.MascotaViewModel


class VoluntarioFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentVoluntarioBinding? = null
    private val binding get() = _binding!!
    private lateinit var mascotaViewModel: MascotaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVoluntarioBinding.inflate(inflater, container, false)
        mascotaViewModel = ViewModelProvider(requireActivity())
            .get(MascotaViewModel::class.java)
        binding.btnregistrarvoluntario.setOnClickListener(this)
        return binding.root
    }
    override fun onClick(v: View?) {
        if(binding.cbaceptarterminos.isChecked){
            binding.btnregistrarvoluntario.isEnabled = false
            //mascotaViewModel.registrarVoluntario()
        }
    }

}