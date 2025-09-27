package model.repository.MascotasDAO;

import model.entities.Mascotas.Mascota;

import java.util.List;

public interface  IMascotasDAO {
    void agregarMascota(Mascota mascota);
    Mascota obtenerPorId(Integer id);
    List<Mascota> obtenerTodos();
    void actualizarMascota(Mascota mascota);
    void cambiarEstadoMascota(Integer id);
    List<Mascota> obtenerPorDuenoId(Integer duenoId);
    List<Mascota> obtenerPorRazaId(Integer razaId);
    // List<Mascotas> obtenerPorEspecieId(Integer especieId);
}
