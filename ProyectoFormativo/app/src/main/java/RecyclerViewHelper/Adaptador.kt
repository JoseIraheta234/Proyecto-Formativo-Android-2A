package RecyclerViewHelper

import Modelo.tbPacientes
import Modelo.tbDetallesPacientes
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jose.edenilson.proyectoformativo.R

class Adaptador(var Datos: List<tbPacientes>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paciente = Datos[position]
        holder.txtNpacientes.text = paciente.id_DetallePaciente.toString()

    }
}