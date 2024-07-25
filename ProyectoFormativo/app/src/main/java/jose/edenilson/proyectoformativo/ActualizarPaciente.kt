package jose.edenilson.proyectoformativo

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val txtNombrePacientesD = findViewById<TextView>(R.id.txtNombrePacientesD)
        val txtHabitacionesD = findViewById<TextView>(R.id.txtHabitacionesD)
        val txtMedicamentosD = findViewById<TextView>(R.id.txtMedicamentosD)
        val txtEnfermedadD = findViewById<TextView>(R.id.txtEnfermedadD)
        val txtCamasD = findViewById<TextView>(R.id.txtCamasD)
        val txtHoraD = findViewById<TextView>(R.id.txtHoraD)






        val id_DetallePaciente = intent.getStringExtra("id_DetallePaciente")
        val id_Habitacion = intent.getIntExtra("id_Habitacion", 0)
        val id_Medicamento = intent.getStringExtra("id_Medicamento")
        val id_Enfermedad = intent.getStringExtra("id_Enfermedad")
        val id_Camas = intent.getIntExtra("id_Camas", 0)
        val HoraMedicamento = intent.getStringExtra("HoraMedicamento")




        txtNombrePacientesD.text = id_DetallePaciente
        txtHabitacionesD.text = id_Habitacion.toString()
        txtMedicamentosD.text = id_Medicamento
        txtEnfermedadD.text = id_Enfermedad
        txtCamasD.text = id_Camas.toString()
        txtHoraD.text = HoraMedicamento

    }
}