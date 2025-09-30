package model.repository.MascotasDAO;

import model.entities.Mascotas.Mascota;

import java.util.List;

public interface  IMascotasDAO {
    int agregarMascota(Mascota mascota, String documentoDueno);
    void agregarMascota(Mascota mascota);
    Mascota obtenerPorId(Integer id);
    List<Mascota> obtenerTodos();
    void actualizarMascota(Mascota mascota);
    void cambiarEstadoMascota(Integer id);
    List<Mascota> obtenerPorDuenoDocumento(Integer documentoDueno);
    List<Mascota> obtenerPorRazaId(Integer razaId);
    List<Mascota> obtenerPorEspecieId(Integer especieId);
}
