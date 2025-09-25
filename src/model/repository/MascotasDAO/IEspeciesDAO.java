package model.repository.MascotasDAO;

import model.entities.Mascotas.Especies;

import java.util.List;

public interface IEspeciesDAO {
    void insertar(Especies especie);
    Especies obtenerPorId(Integer id);
    List<Especies> obtenerTodos();
    void actualizar(Especies especie);
    void eliminar(Integer id);
}
