package model.repository.MascotasDAO;

import model.entities.Mascotas.Especies;
import model.entities.Mascotas.Mascotas;

import java.util.List;

public interface IMascotasDAO {
    void insertar(Mascotas mascota);
    Mascotas obtenerPorId(Integer id);
    List<Mascotas> obtenerTodos();
    void actualizar(Mascotas mascota);
    void eliminar(Integer id);
    List<Mascotas> obtenerPorDuenoId(Integer duenoId);
    List<Mascotas> obtenerPorRazaId(Integer razaId);
}
