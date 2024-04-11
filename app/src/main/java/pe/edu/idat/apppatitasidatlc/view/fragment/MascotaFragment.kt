package pe.edu.idat.apppatitasidatlc.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.apppatitasidatlc.R
import pe.edu.idat.apppatitasidatlc.databinding.FragmentMascotaBinding
import pe.edu.idat.apppatitasidatlc.view.adapter.MascotaAdapter
import pe.edu.idat.apppatitasidatlc.viewmodel.MascotaViewModel


class MascotaFragment : Fragment() {
    private var _binding: FragmentMascotaBinding? = null
    private val binding get() = _binding!!
    private lateinit var mascotaViewModel: MascotaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMascotaBinding.inflate(inflater, container, false)
        binding.rvmascotas.layoutManager = LinearLayoutManager(requireActivity())
        mascotaViewModel = ViewModelProvider(requireActivity())
            .get(MascotaViewModel::class.java)
        listarMascotas()
        return binding.root
    }
    private fun listarMascotas() {
        mascotaViewModel.listarMascotas().observe(viewLifecycleOwner,
            Observer {
                binding.rvmascotas.adapter = MascotaAdapter(it)
            })
    }

}