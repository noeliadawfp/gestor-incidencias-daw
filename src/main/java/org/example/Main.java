package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        Connection con = Conexion.obtenerConexion();

        if (con != null) {
            try {
                // Objeto para lanzar la consulta SQL
                Statement stmt = con.createStatement();
                String sql = "SELECT * FROM usuarios";

                // Ejecutamos la consulta y guardamos
                ResultSet rs = stmt.executeQuery(sql);

                System.out.println("\n--- LISTA DE USUARIOS EN LA BD ---");
                //Recorremos las filas MySQL
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");
                    String rol = rs.getString("rol");

                    System.out.println("ID: " + id + " | Nombre: " + nombre + " | Email: " + email + " | Rol: " + rol);
                }
                System.out.println("----------------------------------\n");

                //Cerramos la conexión
                con.close();

            } catch (Exception e) {
                System.out.println("Error al consultar los datos: " + e.getMessage());
            }
        }
    }
}