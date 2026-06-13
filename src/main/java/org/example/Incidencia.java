package org.example;

public class Incidencia {
    private int id;
    private String descripcion;
    private String estado;

    public Incidencia(int id, String descripcion, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Getters
    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public String getEstado() { return estado; }
}