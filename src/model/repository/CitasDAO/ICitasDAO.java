package model.repository.CitasDAO;

import model.entities.Citas.Citas;

import java.util.List;

public interface ICitasDAO {
    void crear(Citas cita);
    Citas obtenerPorId(int id);
    List<Citas> obtenerTodas();
    void actualizar(Citas cita);
    void eliminar(int id);
}
