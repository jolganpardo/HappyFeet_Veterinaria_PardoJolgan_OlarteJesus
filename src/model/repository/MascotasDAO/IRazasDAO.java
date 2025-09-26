package model.repository.MascotasDAO;

import model.entities.Mascotas.Mascotas;
import model.entities.Mascotas.Razas;

import java.util.List;

public interface IRazasDAO {
    void agregarRaza(Razas raza);
    void actualizarRaza(Razas raza);
    void eliminarRaza(int id);
    Razas obtenerPorId(int id);
    List<Razas> obtenerTodos();
    List<Razas> obtenerPorEspecieId(Integer especieId);
}
