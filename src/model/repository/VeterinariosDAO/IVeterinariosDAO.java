package model.repository.VeterinariosDAO;

import model.entities.Veterinarios.Veterinarios;

import java.util.List;

public interface IVeterinariosDAO {
    void insertar(Veterinarios veterinario);
    void actualizar(Veterinarios veterinario);
    void eliminar(Integer id);
    Veterinarios buscarPorId(Integer id);
    List<Veterinarios> listarTodos();
}
