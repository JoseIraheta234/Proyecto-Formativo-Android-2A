package Modelo

data class tbPacientes(
    val id_Paciente: Int,
    val id_DetallePaciente: String,
    val id_Habitacion: Int,
    val id_Medicamento: String,
    val id_Enfermedad: String,
    val id_Camas: Int,
    val HoraMedicamento: String
)
