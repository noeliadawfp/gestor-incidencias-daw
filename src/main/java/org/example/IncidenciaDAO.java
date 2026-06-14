package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncidenciaDAO {

    public boolean validarUsuario(String user, String pass) {
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND password = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al validar usuario: " + e.getMessage());
            return false;
        }
    }

    public void insertarIncidencia(String titulo, String descripcion, int usuario_id) {
        String sql = "INSERT INTO incidencias (titulo, descripcion, usuario_id) VALUES (?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, descripcion);
            pstmt.setInt(3, usuario_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar: " + e.getMessage());
        }
    }

    public List<Incidencia> obtenerTodasLasIncidencias() {
        List<Incidencia> lista = new ArrayList<>();
        String sql = "SELECT id, descripcion, estado FROM incidencias";
        try (Connection con = Conexion.obtenerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Incidencia(rs.getInt("id"), rs.getString("descripcion"), rs.getString("estado")));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public void actualizarEstado(int id, String nuevoEstado) {
        String sql = "UPDATE incidencias SET estado = ? WHERE id = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, nuevoEstado);
            pstmt.setInt(2, id);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("✅ Incidencia " + id + " actualizada a: " + nuevoEstado);
            } else {
                System.out.println("⚠️ No se encontró ninguna incidencia con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar: " + e.getMessage());
        }
    }

    public void eliminarIncidencia(int id) {
        String sql = "DELETE FROM incidencias WHERE id = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("🗑️ Incidencia " + id + " eliminada correctamente.");
            } else {
                System.out.println("⚠️ No se encontró ninguna incidencia con el ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar: " + e.getMessage());
        }
    }
}