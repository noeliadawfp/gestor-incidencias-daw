package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        Connection con = Conexion.obtenerConexion();

        if (con != null) {
            try {
                //LECTURA DE USUARIOS
                Statement stmt = con.createStatement();
                String sql = "SELECT * FROM usuarios";
                ResultSet rs = stmt.executeQuery(sql);

                System.out.println("\n--- LISTA DE USUARIOS EN LA BD ---");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String email = rs.getString("email");
                    String rol = rs.getString("rol");
                    System.out.println("ID: " + id + " | Nombre: " + nombre + " | Email: " + email + " | Rol: " + rol);
                }
                System.out.println("----------------------------------\n");

                //Cerramos la conexión después de leer
                con.close();

                //INSERCIÓN DE INCIDENCIA (usando nuestro nuevo DAO)
                IncidenciaDAO dao = new IncidenciaDAO();
                dao.insertarIncidencia("Monitor roto", "Problema con el monitor del aula 3", 1);

            } catch (Exception e) {
                System.out.println("Error al procesar los datos: " + e.getMessage());
            }
        }
    }
}