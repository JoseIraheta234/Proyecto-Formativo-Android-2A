package jose.edenilson.proyectoformativo

import Modelo.ClaseConexion
import Modelo.tbPacientes
import RecyclerViewHelper.Adaptador
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaDePacientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_de_pacientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val imgRegresarL = findViewById<ImageView>(R.id.imgRegresarL)
        val rcvListaPacientes = findViewById<RecyclerView>(R.id.rcvListaPacientes)
        rcvListaPacientes.layoutManager = LinearLayoutManager(this)


        imgRegresarL.setOnClickListener {
            val intent = android.content.Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        fun obtenerPacientes(): List<tbPacientes> {
            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT p.id_Paciente, pd.nombre_Paciente, p.id_Habitacion, p.id_Medicamento, p.id_Enfermedad, p.id_Camas, p.HoraMedicamento FROM tbPacientes p INNER JOIN tbDetallesPacientes pd ON p.id_DetallePaciente = pd.id_DetallePaciente")!!
            val listadoPacientes = mutableListOf<tbPacientes>()
            while (resultSet.next()) {
                val id_Paciente = resultSet.getInt("id_Paciente")
                val id_DetallePaciente = resultSet.getString("nombre_Paciente")
                val id_Habitacion = resultSet.getInt("id_Habitacion")
                val id_Medicamento = resultSet.getInt("id_Medicamento")
                val id_Enfermedad = resultSet.getInt("id_Enfermedad")
                val id_Camas = resultSet.getInt("id_Camas")
                val HoraMedicamento = resultSet.getString("HoraMedicamento")

                val valoresjuntos = tbPacientes(id_Paciente,id_DetallePaciente,id_Habitacion,id_Medicamento,id_Enfermedad,id_Camas,HoraMedicamento)
                listadoPacientes.add(valoresjuntos)
            }
            return listadoPacientes
        }


        CoroutineScope(Dispatchers.IO).launch {
            val pacienteDB = obtenerPacientes()
            withContext(Dispatchers.Main){
                val adapter = Adaptador(pacienteDB)
                rcvListaPacientes.adapter = adapter
            }
        }

    }
}