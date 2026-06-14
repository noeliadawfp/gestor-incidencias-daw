package org.example;

import java.util.List;

public class GestorIncidencias {
    private IncidenciaDAO dao = new IncidenciaDAO();
    public List<Incidencia> obtenerTodas() {
        return dao.obtenerTodasLasIncidencias();
    }
    public void registrar(String titulo, String descripcion) {
        dao.insertarIncidencia(titulo, descripcion, 1);
    }
    public void actualizar(int id, String estado) {
        dao.actualizarEstado(id, estado);
    }
    public void eliminar(int id) {
        dao.eliminarIncidencia(id);
    }
    public boolean autenticar(String usuario, String pass) {
        return dao.validarUsuario(usuario, pass);

    }
}