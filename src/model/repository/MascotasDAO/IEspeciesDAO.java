package model.repository.MascotasDAO;

import model.entities.Mascotas.Especies;

import java.util.List;

public interface IEspeciesDAO {
    void agregarEspecie(Especies especie);
    Especies obtenerPorId(Integer id);
    List<Especies> obtenerTodos();
    void actualizarEspecie(Especies especie);
    void eliminarEspecie(Integer id);
}
