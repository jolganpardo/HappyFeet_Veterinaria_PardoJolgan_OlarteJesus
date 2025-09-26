package model.repository.MascotasDAO;

import model.entities.Mascotas.Especies;
import model.entities.Mascotas.Mascotas;

import java.util.List;

public interface  IMascotasDAO {
    void agregarMascota(Mascotas mascota);
    Mascotas obtenerPorId(Integer id);
    List<Mascotas> obtenerTodos();
    void actualizarMascota(Mascotas mascota);
    void cambiarEstadoMascota(Integer id);
    List<Mascotas> obtenerPorDuenoId(Integer duenoId);
    List<Mascotas> obtenerPorRazaId(Integer razaId);
    // List<Mascotas> obtenerPorEspecieId(Integer especieId);
}
