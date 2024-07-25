package RecyclerViewHelper

import Modelo.ClaseConexion
import Modelo.tbPacientes
import Modelo.tbDetallesPacientes
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import jose.edenilson.proyectoformativo.ActualizarPaciente
import jose.edenilson.proyectoformativo.AgregarPaciente
import jose.edenilson.proyectoformativo.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Adaptador(var Datos: List<tbPacientes>) : RecyclerView.Adapter<ViewHolder>() {



    fun actualizarPaciente(id_Paciente: Int,id_DetallePaciente: String,id_Habitacion: Int,id_Medicamento: Int,id_Enfermedad: Int,id_Camas: Int,HoraMedicamento: String){
        GlobalScope.launch(Dispatchers.IO){
            val objConexion = ClaseConexion().cadenaConexion()
            val updatePaciente = objConexion?.prepareStatement("update tbPacientes set id_DetallePaciente = ?,id_Habitacion = ?,id_Medicamento = ?,id_Enfermedad = ?,id_Camas = ? where id_Paciente = ?")!!
            updatePaciente.setInt(1,id_Paciente)
            updatePaciente.setString(2,id_DetallePaciente)
            updatePaciente.setInt(3,id_Habitacion)
            updatePaciente.setInt(4,id_Medicamento)
            updatePaciente.setInt(5,id_Enfermedad)
            updatePaciente.setInt(6,id_Camas)
            updatePaciente.setString(7,HoraMedicamento)
            updatePaciente.executeUpdate()

            val commit = objConexion?.prepareStatement("commit")!!
            commit.executeUpdate()


        }


    }

    fun eliminarPaciente(id_Paciente: Int,posicion: Int){

        val listaDatos =Datos.toMutableList()
        listaDatos.removeAt(posicion)

        GlobalScope.launch(Dispatchers.IO){
            val objConexion = ClaseConexion().cadenaConexion()
            val delPaciente = objConexion?.prepareStatement("delete tbPacientes where id_Paciente = ?")!!
            delPaciente.setInt(1,id_Paciente)
            delPaciente.executeUpdate()

            val commit = objConexion.prepareStatement("commit")
            commit.executeUpdate()
        }

        Datos = listaDatos.toList()
        notifyItemRemoved(posicion)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paciente = Datos[position]
        holder.txtNpacientes.text = paciente.id_DetallePaciente

        val item = Datos[position]


        holder.imgBorrar.setOnClickListener {
            val context =  holder.itemView.context

            val builder = AlertDialog.Builder(context)

            builder.setTitle("¿Estas seguro?")

            builder.setMessage("¿Desea eliminar el registro?")


            builder.setNegativeButton("No"){dialog,which ->

            }

            builder.setPositiveButton("Si"){dialog,which ->
                eliminarPaciente(item.id_Paciente, position)
            }



            val alertDialog = builder.create()

            alertDialog.show()
        }



        holder.imgEditar.setOnClickListener {
            val intent = android.content.Intent(holder.itemView.context, ActualizarPaciente::class.java)
            holder.itemView.context.startActivity(intent)
        }




    }


}