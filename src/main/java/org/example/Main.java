package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {

        GestorIncidencias gestor = new GestorIncidencias();
        Scanner sc = new Scanner(System.in);

        // 1. LOGIN
        System.out.println("--- ACCESO AL SISTEMA ---");
        System.out.print("Usuario: ");
        String user = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        if (gestor.autenticar(user, pass)) {
            System.out.println("✅ Acceso concedido.");

            int opcion = -1;

            // 2. MENÚ PRINCIPAL
            while (opcion != 0) {
                System.out.println("\n--- GESTOR DE INCIDENCIAS ---");
                System.out.println("1: Ver | 2: Crear | 3: Actualizar | 4: Eliminar | 0: Salir");
                System.out.print("Elige una opción: ");

                try {
                    opcion = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer

                    switch (opcion) {
                        case 1:
                            List<Incidencia> lista = gestor.obtenerTodas();
                            for (Incidencia i : lista) {
                                System.out.println("ID: " + i.getId() + " | Estado: " + i.getEstado() + " | Desc: " + i.getDescripcion());
                            }
                            break;
                        case 2:
                            System.out.println("Escribe el título:"); String t = sc.nextLine();
                            System.out.println("Escribe la descripción:"); String d = sc.nextLine();
                            gestor.registrar(t, d);
                            System.out.println("✅ Incidencia creada.");
                            break;
                        case 3:
                            System.out.println("ID a actualizar:"); int idUp = sc.nextInt(); sc.nextLine();
                            System.out.println("Nuevo estado:"); String est = sc.nextLine();
                            gestor.actualizar(idUp, est);
                            break;
                        case 4:
                            System.out.println("ID a eliminar:"); int idDel = sc.nextInt(); sc.nextLine();
                            gestor.eliminar(idDel);
                            break;
                        case 0:
                            System.out.println("👋 ¡Sesión cerrada!");
                            break;
                        default:
                            System.out.println("⚠️ Opción no válida.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("❌ ERROR: Debes introducir un número.");
                    sc.nextLine();
                    opcion = -1;
                }
            }
        } else {
            System.out.println("❌ Usuario o contraseña incorrectos.");
        }

        sc.close();
    }
}