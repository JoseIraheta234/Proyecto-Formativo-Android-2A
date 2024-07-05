package jose.edenilson.proyectoformativo

import Modelo.ClaseConexion
import Modelo.tbDetallesPacientes
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class AgregarPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_paciente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spPaciente = findViewById<Spinner>(R.id.spPaciente)
        val spEnfermedad = findViewById<Spinner>(R.id.spEnfermedad)
        val spMedicamento = findViewById<Spinner>(R.id.spMedicamento)
        val spCama = findViewById<Spinner>(R.id.spCama)
        val spHabitacion = findViewById<Spinner>(R.id.spHabitacion)
        val txtHora = findViewById<EditText>(R.id.txtHora)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)


        fun obtenerPacientes():List<tbDetallesPacientes>{

            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("Select * From tbDetallesPacientes")!!

            val listadoPacientes = mutableListOf<tbDetallesPacientes>()

            while (resultSet.next()){
                val id_DetallePaciente = resultSet.getInt("id_DetallePaciente")
                val nombre_Paciente = resultSet.getString("nombre_Paciente")
                val apellido_Paciente = resultSet.getString("apellido_Paciente")
                val edad = resultSet.getInt("edad")
                val unPacienteCompleto = tbDetallesPacientes(id_DetallePaciente,nombre_Paciente,apellido_Paciente,edad)
                listadoPacientes.add(unPacienteCompleto)

            }
            return listadoPacientes
        }

        GlobalScope.launch(Dispatchers.IO){
            val MilistadoDePacientes = obtenerPacientes()
            val nombrePacientes = MilistadoDePacientes.map { it.nombre_Paciente }

            withContext(Dispatchers.Main){
                val miAdaptador = ArrayAdapter(this@AgregarPaciente,android.R.layout.simple_spinner_item,nombrePacientes)
                spPaciente.adapter = miAdaptador
            }
        }


        btnAgregar.setOnClickListener {
             GlobalScope.launch(Dispatchers.IO) {
                 val Claseconexion = ClaseConexion().cadenaConexion()

                 val addPacientes =
                     Claseconexion?.prepareStatement("insert into tbPacientes(id_Paciente,id_DetallePaciente,id_Enfermedad,id_Medicamento,id_Camas,id_Habitacion,HoraMedicamento) values(?,?,?,?,?,?,?)")!!
                  addPacientes.setString(1, UUID.randomUUID().toString())
                  addPacientes.setString(2, spPaciente.selectedItem.toString())


             }
        }
    }


}