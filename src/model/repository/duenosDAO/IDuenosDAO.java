package model.repository.duenosDAO;

import model.entities.Duenos.Dueno;

import java.util.List;

public interface IDuenosDAO {
    void insertar(Dueno dueno);
    Dueno buscarPorId(int id);
    List<Dueno> listar();
    void actualizar(Dueno dueno);
    void eliminar(int id);
}
