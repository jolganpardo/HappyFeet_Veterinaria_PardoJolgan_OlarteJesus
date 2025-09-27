package model.repository.HistorialMedicoDAO;

import model.entities.Historial_Medico.HistorialMedico;
import java.util.List;

public interface IHistorialMedicoDAO {
    void agregarHistorial(HistorialMedico historial);
    HistorialMedico obtenerPorId(Integer id);
    List<HistorialMedico> obtenerTodos();
    List<HistorialMedico> obtenerPorMascotaId(Integer mascotaId);
    void actualizarHistorial(HistorialMedico historial);
    void eliminarHistorial(Integer id);
}
