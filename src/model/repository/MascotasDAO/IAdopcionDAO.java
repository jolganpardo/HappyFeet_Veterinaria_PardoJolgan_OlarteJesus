package model.repository.MascotasDAO;

import model.entities.Mascotas.Adopcion;

import java.util.List;

public interface IAdopcionDAO {
    void agregarAdopcion(Adopcion adopcion);
    Adopcion obtenerPorId(Integer id);
    List<Adopcion> obtenerTodos();
    void actualizarAdopcion(Adopcion adopcion);
    void eliminarAdopcion(Integer id);
}
