package model.repository.MascotasDAO;

import model.entities.Mascotas.Razas;

import java.util.List;

public interface IRazasDAO {
    void insertar(Razas raza);
    void actualizar(Razas raza);
    void eliminar(int id);
    Razas obtenerPorId(int id);
    List<Razas> obtenerTodos();
}
