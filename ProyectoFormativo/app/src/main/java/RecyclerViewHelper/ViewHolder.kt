package RecyclerViewHelper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jose.edenilson.proyectoformativo.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val txtNpacientes : TextView = view.findViewById(R.id.txtNpacientes)
    val imgEditar : ImageView = view.findViewById(R.id.imgEditar)
    val imgBorrar : ImageView = view.findViewById(R.id.imgBorrar)

}