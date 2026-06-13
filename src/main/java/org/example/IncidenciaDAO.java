package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IncidenciaDAO { // <-- Inicio de la clase

    public void insertarIncidencia(String titulo, String descripcion, int usuario_id) {
        // La tabla pide: titulo, descripcion y usuario_id (el id se genera solo)
        String sql = "INSERT INTO incidencias (titulo, descripcion, usuario_id) VALUES (?, ?, ?)";

        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, titulo);
            pstmt.setString(2, descripcion);
            pstmt.setInt(3, usuario_id);

            pstmt.executeUpdate();
            System.out.println("✅ Incidencia guardada con éxito.");

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar: " + e.getMessage());
        }
    }
}