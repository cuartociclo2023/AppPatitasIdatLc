package pe.edu.idat.apppatitasidatlc.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.apppatitasidatlc.databinding.ItemMascotaBinding
import pe.edu.idat.apppatitasidatlc.retrofit.response.MascotaResponse

class MascotaAdapter(private var listaMascotas: List<MascotaResponse>) : RecyclerView.Adapter<MascotaAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemMascotaBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaAdapter.ViewHolder {
        val binding = ItemMascotaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MascotaAdapter.ViewHolder, position: Int) {
        with(holder){
            with(listaMascotas[position]){
                binding.tvnommascota.text = nommascota
                binding.tvfechaperdida.text = fechaperdida
                binding.tvcontacto.text = contacto
                binding.tvlugar.text = lugar
                Glide.with(itemView.context)
                    .load(urlimagen).into(binding.ivmascota)
            }
        }
    }
    override fun getItemCount(): Int = listaMascotas.size
}