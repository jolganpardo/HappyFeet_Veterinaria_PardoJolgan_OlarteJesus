package model.repository.CitasDAO;

import model.entities.Citas.Cita;
import java.util.List;

public interface ICitaDAO {
    int agregarCita(Cita cita);
    Cita obtenerPorId(Integer id);
    List<Cita> obtenerTodos();
    void actualizarCita(Cita cita);
    void cancelarCita(Integer id);
    List<Cita> obtenerPorVeterinarioId(Integer veterinarioId);
}
