package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/gestor_incidencias_db?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión BBDD: OK");
        } catch (ClassNotFoundException e) {
            System.out.println("Error de conexión con base de datos");
        } catch (SQLException e) {
            System.out.println("Error de MySQL: " + e.getMessage());
        }
        return conexion;
    }
}
