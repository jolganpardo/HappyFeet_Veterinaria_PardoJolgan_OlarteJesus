package model.repository.MascotasDAO;

import model.entities.Mascotas.Raza;

import java.util.List;

public interface IRazasDAO {
    void agregarRaza(Raza raza);
    void actualizarRaza(Raza raza);
    void eliminarRaza(int id);
    Raza obtenerPorId(int id);
    List<Raza> obtenerTodos();
    List<Raza> obtenerPorEspecieId(Integer especieId);
}
