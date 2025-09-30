package model.repository.MascotasDAO;

import model.entities.Mascotas.Especie;

import java.util.List;

public interface IEspeciesDAO {
    Especie agregarEspecie(Especie especie);
    Especie obtenerPorId(Integer id);
    List<Especie> obtenerTodos();
    void actualizarEspecie(Especie especie);
    void eliminarEspecie(Integer id);
}