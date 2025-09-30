package model.repository.VeterinariosDAO;

import model.entities.Veterinarios.Veterinario;

import java.util.List;

public interface IVeterinariosDAO {
    Integer insertar(Veterinario veterinario);
    void actualizar(Veterinario veterinario);
    void eliminar(Integer id);
    Veterinario buscarPorId(Integer id);
    List<Veterinario> listarTodos();
}
