package model.repository.HistorialMedicoDAO;

import model.entities.Historial_Medico.EventoTipos;
import java.util.List;

public interface IEventoTipoDAO {
    void agregarEventoTipo(EventoTipos evento);
    EventoTipos obtenerPorId(Integer id);
    EventoTipos obtenerPorNombre(String nombre);
    List<EventoTipos> obtenerTodos();
    void actualizarEventoTipo(EventoTipos evento);
    void eliminarEventoTipo(Integer id);
}
