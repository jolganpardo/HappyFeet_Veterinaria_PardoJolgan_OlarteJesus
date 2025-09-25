package model.repository.CitasDAO;

import model.entities.Citas.Cita_Estados;

import java.util.List;

public interface ICitas_EstadosDAO {
    void crear(Cita_Estados estado);
    Cita_Estados obtenerPorId(int id);
    Cita_Estados obtenerPorNombre(String nombre);
    List<Cita_Estados> obtenerTodos();
    void actualizar(Cita_Estados estado);
    void eliminar(int id);
}
