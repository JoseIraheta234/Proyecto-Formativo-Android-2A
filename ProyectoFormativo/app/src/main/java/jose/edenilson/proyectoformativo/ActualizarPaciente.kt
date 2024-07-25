package jose.edenilson.proyectoformativo

import Modelo.ClaseConexion
import Modelo.tbDetallesPacientes
import Modelo.tbEnfermedades
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


        val imgEditarNombre = findViewById<ImageView>(R.id.imgEditarNombre)
        val imgEditarHabitaciones = findViewById<ImageView>(R.id.imgEditarHabitaciones)
        val imgEditarMedicamentos = findViewById<ImageView>(R.id.imgEditarMedicamentos)
        val imgEditarEnfermedad = findViewById<ImageView>(R.id.imgEditarEnfermedad)
        val imgEditarCamas = findViewById<ImageView>(R.id.imgEditarCamas)
        val imgEditarHora = findViewById<ImageView>(R.id.imgEditarHora)

        val txtNombreDetalles = findViewById<EditText>(R.id.txtEditarNombre)
        val txtHabitaciones = findViewById<EditText>(R.id.txtEditarHabitaciones)
        val txtMedicamentos = findViewById<EditText>(R.id.txtEditarMedicamentos)
        val txtEnfermedad = findViewById<EditText>(R.id.txtEditarEnfermedad)
        val txtCamas = findViewById<EditText>(R.id.txtEditarCamas)
        val txtHora = findViewById<EditText>(R.id.txtEditarHora)

        val btnActualizar = findViewById<TextView>(R.id.btnActualizar)
        val spEnfermedadD = findViewById<Spinner>(R.id.spEnfermedadD)




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



        val id_Paciente = intent.getIntExtra("id_Paciente", 0)



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
        }






        




        val id_DetallePaciente = intent.getStringExtra("id_DetallePaciente")
        val id_Habitacion = intent.getIntExtra("id_Habitacion", 0)
        val id_Medicamento = intent.getStringExtra("id_Medicamento")
        val id_Enfermedad = intent.getStringExtra("id_Enfermedad")
        val id_Camas = intent.getIntExtra("id_Camas", 0)
        val HoraMedicamento = intent.getStringExtra("HoraMedicamento")


        txtNombreDetalles.setText(id_DetallePaciente)
        txtNombreDetalles.isEnabled = false

        imgEditarNombre.setOnClickListener {
            txtNombreDetalles.isEnabled = true
        }



        txtMedicamentos.setText(id_Medicamento)
        txtMedicamentos.isEnabled = false
        imgEditarMedicamentos.setOnClickListener {
            txtMedicamentos.isEnabled = true
        }



        txtHora.setText(HoraMedicamento)
        txtHora.isEnabled = false
        imgEditarHora.setOnClickListener {
            txtHora.isEnabled = true
        }

        txtHabitaciones.setText(id_Habitacion.toString())
        txtHabitaciones.isEnabled = false
        imgEditarHabitaciones.setOnClickListener {
            txtHabitaciones.isEnabled = true
        }


        txtCamas.setText(id_Camas.toString())
        txtCamas.isEnabled = false
        imgEditarCamas.setOnClickListener {
            txtCamas.isEnabled = true
        }









    }
}