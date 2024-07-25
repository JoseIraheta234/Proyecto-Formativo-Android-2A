package Modelo

data class tbPacientes(
    val id_Paciente: Int,
    val id_DetallePaciente: String,
    val id_Habitacion: Int,
    val id_Medicamento: Int,
    val id_Enfermedad: Int,
    val id_Camas: Int,
    val HoraMedicamento: String
)
