package Modelo

import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {

    fun cadenaConexion(): Connection?{

        try {
            val url = "jdbc:oracle:thin:@192.168.0.4:1521:xe"
            val usuario = "PFPB"
            val contrasena = "PFPB"

            val connection = DriverManager.getConnection(url,usuario,contrasena)
            return connection

        }

        catch (e: Exception){
            println("Este es el error: $e")
            return null
        }
    }
}