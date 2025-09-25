package model.repository.Historial_MedicoDAO;

import model.entities.Historial_Medico.Historial_Medico;

import java.util.List;

public interface Historial_MedicoDAO {
    void insertar(Historial_Medico historial);
    Historial_Medico buscarPorId(int id);
    List<Historial_Medico> listar();
    List<Historial_Medico> listarPorMascota(int mascotaId);
    void actualizar(Historial_Medico historial);
    void eliminar(int id);
}
