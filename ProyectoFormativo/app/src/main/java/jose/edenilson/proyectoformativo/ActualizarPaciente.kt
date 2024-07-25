package jose.edenilson.proyectoformativo

import Modelo.ClaseConexion
import Modelo.tbCamas
import Modelo.tbDetallesPacientes
import Modelo.tbEnfermedades
import Modelo.tbHabitaciones
import Modelo.tbMedicamentos
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActualizarPaciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actualizar_paciente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val imgEditarHabitaciones = findViewById<ImageView>(R.id.imgEditarHabitaciones)
        val imgEditarMedicamentos = findViewById<ImageView>(R.id.imgEditarMedicamentos)
        val imgEditarEnfermedad = findViewById<ImageView>(R.id.imgEditarEnfermedad)
        val imgEditarCamas = findViewById<ImageView>(R.id.imgEditarCamas)
        val imgEditarHora = findViewById<ImageView>(R.id.imgEditarHora)

        val txtNombreDetalles = findViewById<TextView>(R.id.txtNombre)
        val spHabitaciones = findViewById<Spinner>(R.id.spHabitaciones)
        val spMedicamentos = findViewById<Spinner>(R.id.spMedicamentos)
        val spCamas = findViewById<Spinner>(R.id.spCamas)
        val txtHora = findViewById<EditText>(R.id.txtEditarHora)

        val btnActualizar = findViewById<TextView>(R.id.btnActualizar)
        val spEnfermedadD = findViewById<Spinner>(R.id.spEnfermedadD)

        val id_Paciente = intent.getIntExtra("id_Paciente", 0)


        val id_DetallePaciente = intent.getStringExtra("id_DetallePaciente")
        txtNombreDetalles.text = id_DetallePaciente

       

        fun obtenerEnfermedadesD():List<tbEnfermedades>{

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
            val verListaEnfermedades = obtenerEnfermedadesD()
            val numero_Enfermedades = verListaEnfermedades.map { it.nombre_Enfermedad  }

            withContext(Dispatchers.Main) {
                val Adaptador = ArrayAdapter(
                    this@ActualizarPaciente,
                    android.R.layout.simple_spinner_dropdown_item,
                    numero_Enfermedades
                )
                spEnfermedadD.adapter = Adaptador
            }
        }

        spEnfermedadD.isEnabled = false

        imgEditarEnfermedad.setOnClickListener {
            spEnfermedadD.isEnabled = true
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
                    this@ActualizarPaciente,
                    android.R.layout.simple_spinner_dropdown_item,
                    numero_Camas
                )
                spCamas.adapter = Adaptador
            }
        }

       spCamas.isEnabled = false
        imgEditarCamas.setOnClickListener {
            spCamas.isEnabled = true
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
                    this@ActualizarPaciente,
                    android.R.layout.simple_spinner_dropdown_item,
                    numero_Habitaciones
                )
                spHabitaciones.adapter = Adaptador
            }
        }

        spHabitaciones.isEnabled = false
        imgEditarHabitaciones.setOnClickListener {
            spHabitaciones.isEnabled = true
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
                    this@ActualizarPaciente,
                    android.R.layout.simple_spinner_dropdown_item,
                    numero_Medicamentos
                )
                spMedicamentos.adapter = Adaptador
            }
        }

        spMedicamentos.isEnabled = false
        imgEditarMedicamentos.setOnClickListener {
            spMedicamentos.isEnabled = true
        }







        btnActualizar.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val Enfermedades = obtenerEnfermedadesD()
                val objConexion = ClaseConexion().cadenaConexion()
                val updatePaciente =
                    objConexion?.prepareStatement("update tbPacientes set id_Enfermedad = ? where id_Paciente = ?")!!
                updatePaciente.setInt(1,Enfermedades[spEnfermedadD.selectedItemPosition].id_Enfermedad)
                updatePaciente.setInt(2, id_Paciente)
                updatePaciente.executeUpdate()
                val commit = objConexion?.prepareStatement("commit")!!
                commit.executeUpdate()
            }

            GlobalScope.launch(Dispatchers.IO) {
                val Camas = obtenerCamas()
                val objConexion = ClaseConexion().cadenaConexion()
                val updateCama =
                    objConexion?.prepareStatement("update tbPacientes set id_Camas = ? where id_Paciente = ?")!!
                updateCama.setInt(1,Camas[spCamas.selectedItemPosition].id_Camas)
                updateCama.setInt(2, id_Paciente)
                updateCama.executeUpdate()
                val commit = objConexion?.prepareStatement("commit")!!
                commit.executeUpdate()
            }

            GlobalScope.launch(Dispatchers.IO) {
                val Habitaciones = obtenerHabitaciones()
                val objConexion = ClaseConexion().cadenaConexion()
                val updateCama =
                    objConexion?.prepareStatement("update tbPacientes set id_Habitacion = ? where id_Paciente = ?")!!
                updateCama.setInt(1,Habitaciones[spCamas.selectedItemPosition].id_Habitacion)
                updateCama.setInt(2, id_Paciente)
                updateCama.executeUpdate()
                val commit = objConexion?.prepareStatement("commit")!!
                commit.executeUpdate()
            }

            GlobalScope.launch(Dispatchers.IO) {
                val Medicamentos = obtenerMedicamentos()
                val objConexion = ClaseConexion().cadenaConexion()
                val updateCama =
                    objConexion?.prepareStatement("update tbPacientes set id_Medicamento = ? where id_Paciente = ?")!!
                updateCama.setInt(1,Medicamentos[spCamas.selectedItemPosition].id_Medicamento)
                updateCama.setInt(2, id_Paciente)
                updateCama.executeUpdate()
                val commit = objConexion?.prepareStatement("commit")!!
                commit.executeUpdate()
            }
        }






        













    }
}