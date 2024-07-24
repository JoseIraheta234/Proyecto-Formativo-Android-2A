package jose.edenilson.proyectoformativo

import Modelo.ClaseConexion
import Modelo.tbCamas
import Modelo.tbDetallesPacientes
import Modelo.tbEnfermedades
import Modelo.tbHabitaciones
import Modelo.tbMedicamentos
import RecyclerViewHelper.Adaptador
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
        val imgRegresarA = findViewById<ImageView>(R.id.imgRegresarA)


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

        CoroutineScope(Dispatchers.IO).launch {
            val verListaPacientes = obtenerPacientes()
            val nombre_Paciente = verListaPacientes.map { it.nombre_Paciente .plus(" ").plus(it.apellido_Paciente)  }

            withContext(Dispatchers.Main) {
                val Adaptador = ArrayAdapter(
                    this@AgregarPaciente,
                    android.R.layout.simple_spinner_dropdown_item,
                    nombre_Paciente
                )
                spPaciente.adapter = Adaptador
            }
        }



        fun obtenerCamas():List<tbCamas>{

            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("Select * From tbCamas")!!

            val listadoCamas = mutableListOf<tbCamas>()

            while (resultSet.next()){
                val id_Camas = resultSet.getInt("id_Camas")
                val numero_Camas = resultSet.getInt("numero_Camas")
                val unaCamaCompleto = tbCamas(id_Camas,numero_Camas)
                listadoCamas.add(unaCamaCompleto)

            }
            return listadoCamas
        }

        CoroutineScope(Dispatchers.IO).launch {
            val verListaCamas = obtenerCamas()
            val numero_Camas = verListaCamas.map { it.numero_Camas  }

            withContext(Dispatchers.Main) {
                val Adaptador = ArrayAdapter(
                    this@AgregarPaciente,
                    android.R.layout.simple_spinner_dropdown_item,
                    numero_Camas
                )
                spCama.adapter = Adaptador
            }
        }

        fun obtenerHabitaciones():List<tbHabitaciones>{

            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("Select * From tbHabitaciones")!!

            val listadoHabitaciones = mutableListOf<tbHabitaciones>()

            while (resultSet.next()){
                val id_Habitacion = resultSet.getInt("id_Habitacion")
                val numero_Habitacion = resultSet.getInt("numero_Habitacion")
                val unaHabitacionCompleta = tbHabitaciones(id_Habitacion, numero_Habitacion)
                listadoHabitaciones.add(unaHabitacionCompleta)
            }
            return listadoHabitaciones
        }

        CoroutineScope(Dispatchers.IO).launch {
            val verListaHabitaciones = obtenerHabitaciones()
            val numero_Habitaciones = verListaHabitaciones.map { it.numero_Habitacion  }

            withContext(Dispatchers.Main) {
                val Adaptador = ArrayAdapter(
                    this@AgregarPaciente,
                    android.R.layout.simple_spinner_dropdown_item,
                    numero_Habitaciones
                )
                spHabitacion.adapter = Adaptador
            }
        }


        fun obtenerMedicamentos():List<tbMedicamentos>{

            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("Select * From tbMedicamentos")!!

            val listadoMedicamentos = mutableListOf<tbMedicamentos>()

            while (resultSet.next()){
                val id_Medicamento = resultSet.getInt("id_Medicamento")
                val nombre_Medicamento = resultSet.getString("nombre_Medicamento")
                val unMedicamentoCompleto = tbMedicamentos(id_Medicamento,nombre_Medicamento)
                listadoMedicamentos.add(unMedicamentoCompleto)

            }
            return listadoMedicamentos
        }

        CoroutineScope(Dispatchers.IO).launch {
            val verListaMedicamentos = obtenerMedicamentos()
            val numero_Medicamentos = verListaMedicamentos.map { it.nombre_Medicamento  }

            withContext(Dispatchers.Main) {
                val Adaptador = ArrayAdapter(
                    this@AgregarPaciente,
                    android.R.layout.simple_spinner_dropdown_item,
                    numero_Medicamentos
                )
                spMedicamento.adapter = Adaptador
            }
        }



        fun obtenerEnfermedades():List<tbEnfermedades>{

            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("Select * From tbEnfermedades")!!

            val listadoEnfermedades = mutableListOf<tbEnfermedades>()

            while (resultSet.next()){
                val id_Enfermedad = resultSet.getInt("id_Enfermedad")
                val nombre_Enfermedad = resultSet.getString("nombre_Enfermedad")
                val unaEnfermedadCompleta = tbEnfermedades(id_Enfermedad,nombre_Enfermedad)
                listadoEnfermedades.add(unaEnfermedadCompleta)

            }
            return listadoEnfermedades
        }

        CoroutineScope(Dispatchers.IO).launch {
            val verListaEnfermedades = obtenerEnfermedades()
            val numero_Enfermedades = verListaEnfermedades.map { it.nombre_Enfermedad  }

            withContext(Dispatchers.Main) {
                val Adaptador = ArrayAdapter(
                    this@AgregarPaciente,
                    android.R.layout.simple_spinner_dropdown_item,
                    numero_Enfermedades
                )
                spEnfermedad.adapter = Adaptador
            }
        }

        btnAgregar.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {

                val Pacientes = obtenerPacientes()
                val Habitaciones = obtenerHabitaciones()
                val Medicamentos = obtenerMedicamentos()
                val Enfermedades = obtenerEnfermedades()
                val Camas = obtenerCamas()

                val claseConexion = ClaseConexion().cadenaConexion()

                val add0Paciente =
                    claseConexion?.prepareStatement("insert into tbPacientes(id_DetallePaciente,id_Habitacion,id_Medicamento,id_Enfermedad,id_Camas,HoraMedicamento)values(?,?,?,?,?,?)")!!
                add0Paciente.setInt(1, Pacientes[spPaciente.selectedItemPosition].id_DetallePaciente)
                add0Paciente.setInt(2, Habitaciones[spHabitacion.selectedItemPosition].id_Habitacion)
                add0Paciente.setInt(3, Medicamentos[spMedicamento.selectedItemPosition].id_Medicamento)
                add0Paciente.setInt(4, Enfermedades[spEnfermedad.selectedItemPosition].id_Enfermedad)
                add0Paciente.setInt(5, Camas[spCama.selectedItemPosition].id_Camas)
                add0Paciente.setString(6, txtHora.text.toString())
                add0Paciente.executeQuery()
            }
        }

        imgRegresarA.setOnClickListener {
            val intent = android.content.Intent(this, MainActivity::class.java)
            startActivity(intent)
        }





    }


}