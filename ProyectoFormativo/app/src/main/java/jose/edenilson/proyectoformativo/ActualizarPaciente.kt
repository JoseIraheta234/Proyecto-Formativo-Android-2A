package jose.edenilson.proyectoformativo

import Modelo.ClaseConexion
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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






        val id_DetallePaciente = intent.getStringExtra("id_DetallePaciente")
        val id_Habitacion = intent.getIntExtra("id_Habitacion", 0)
        val id_Medicamento = intent.getStringExtra("id_Medicamento")
        val id_Enfermedad = intent.getStringExtra("id_Enfermedad")
        val id_Camas = intent.getIntExtra("id_Camas", 0)
        val HoraMedicamento = intent.getStringExtra("HoraMedicamento")
        val id_Paciente = intent.getIntExtra("id_Paciente", 0)





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

        txtEnfermedad.setText(id_Enfermedad)
        txtEnfermedad.isEnabled = false
        imgEditarEnfermedad.setOnClickListener {
            txtEnfermedad.isEnabled = true
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



        btnActualizar.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                val objConexion = ClaseConexion().cadenaConexion()
                val updatePaciente =
                    objConexion?.prepareStatement("update tbPacientes set id_DetallePaciente = ?,id_Habitacion = ?,id_Medicamento = ?,id_Enfermedad = ?,id_Camas = ?, HoraMedicamento = ?  where id_Paciente = ?")!!
                updatePaciente.setString(1, txtNombreDetalles.text.toString())
                updatePaciente.setInt(2, txtHabitaciones.text.toString().toInt())
                updatePaciente.setString(3, txtMedicamentos.text.toString())
                updatePaciente.setString(4, txtEnfermedad.text.toString())
                updatePaciente.setInt(5, txtCamas.text.toString().toInt())
                updatePaciente.setString(6, txtHora.text.toString())
                updatePaciente.setInt(7, id_Paciente)
                updatePaciente.executeUpdate()

                val commit = objConexion?.prepareStatement("commit")!!
                commit.executeUpdate()


            }
        }





    }
}