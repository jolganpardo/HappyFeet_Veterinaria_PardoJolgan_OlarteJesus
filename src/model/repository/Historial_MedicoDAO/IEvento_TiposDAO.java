package model.repository.Historial_MedicoDAO;

import model.entities.Historial_Medico.Evento_Tipos;

import java.util.List;

public interface IEvento_TiposDAO {
    void insertar(Evento_Tipos eventoTipo);
    Evento_Tipos buscarPorId(int id);
    List<Evento_Tipos> listar();
    void actualizar(Evento_Tipos eventoTipo);
    void eliminar(int id);
}
