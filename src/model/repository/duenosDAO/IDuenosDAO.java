package model.repository.duenosDAO;

import model.entities.Duenos.Dueno;

import java.util.List;

public interface IDuenosDAO {
    void agregarDueno(Dueno dueno);
    Dueno buscarPorId(int id);
    List<Dueno> listarDuenos();
    void actualizarDuenos(Dueno dueno);
    void eliminarDueno(int id);
}
